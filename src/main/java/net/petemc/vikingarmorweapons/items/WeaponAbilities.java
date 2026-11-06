package net.petemc.vikingarmorweapons.items;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Area effects of the melee weapons (since 1.1.0):
 * <ul>
 *   <li>Battle Axe – cleave: a fully charged hit also damages enemies next to the target (like the vanilla sword sweep).</li>
 *   <li>Hammer – shockwave: a critical hit (attack while falling) knocks back all enemies around the target.</li>
 * </ul>
 * Vanilla resets the attack cooldown before {@code Item.hurtEnemy} is called, so the attack state is captured
 * at the very start of the attack ({@link #captureAttack}, called from the loader's attack event) and read
 * in {@code hurtEnemy}, which only runs when the main hit actually did damage.
 */
public final class WeaponAbilities {
    public static final float CLEAVE_DAMAGE_FACTOR = 0.5F;      // share of the attack damage dealt to the other enemies
    public static final double SHOCKWAVE_RADIUS = 2.5D;
    public static final float SHOCKWAVE_DAMAGE = 2.0F;
    public static final float SHOCKWAVE_KNOCKBACK = 1.0F;       // about as strong as Knockback II

    private record AttackState(int tick, boolean fullyCharged, boolean critical) {
    }

    // Server thread only (captureAttack ignores the client side)
    private static final Map<Player, AttackState> ATTACKS = new WeakHashMap<>();

    private WeaponAbilities() {
    }

    /** Called before vanilla handles a player's melee attack. Same conditions as Player.attack. */
    public static void captureAttack(Player player) {
        if (player.level().isClientSide()) {
            return;
        }
        boolean charged = player.getAttackStrengthScale(0.5F) > 0.9F;
        boolean critical = charged && player.fallDistance > 0.0 && !player.onGround() && !player.onClimbable()
                && !player.isInWater() && !player.isMobilityRestricted() && !player.isPassenger()
                && !player.isSprinting();
        ATTACKS.put(player, new AttackState(player.tickCount, charged, critical));
    }

    /** Returns the state captured for the attack that is being processed right now, or null. */
    private static AttackState takeAttack(Player player) {
        AttackState state = ATTACKS.remove(player);
        return state != null && state.tick() == player.tickCount ? state : null;
    }

    /** Battle Axe: fully charged hits also strike enemies next to the target. */
    public static void cleave(Player player, LivingEntity target) {
        AttackState state = takeAttack(player);
        if (state == null || !state.fullyCharged() || !(player.level() instanceof ServerLevel level)) {
            return;
        }
        float damage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * CLEAVE_DAMAGE_FACTOR;
        float yaw = player.getYRot() * Mth.DEG_TO_RAD;
        // Same area as the vanilla sword sweep
        for (LivingEntity other : level.getEntitiesOfClass(LivingEntity.class,
                target.getBoundingBox().inflate(1.0D, 0.25D, 1.0D))) {
            if (isAffected(player, target, other) && player.distanceToSqr(other) < 9.0D) {
                other.knockback(0.4F, Mth.sin(yaw), -Mth.cos(yaw));
                other.hurtServer(level, player.damageSources().playerAttack(player), damage);
            }
        }
        // Sweep particle + sound (Player.sweepAttack() no longer exists, same effect as Player.doSweepAttack)
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
        double dx = -Mth.sin(yaw);
        double dz = Mth.cos(yaw);
        level.sendParticles(ParticleTypes.SWEEP_ATTACK, player.getX() + dx, player.getY(0.5D), player.getZ() + dz, 0, dx, 0.0D, dz, 0.0D);
    }

    /** Hammer: critical hits knock back all enemies around the target. */
    public static void shockwave(Player player, LivingEntity target) {
        AttackState state = takeAttack(player);
        if (state == null || !state.critical() || !(player.level() instanceof ServerLevel level)) {
            return;
        }
        double radius = SHOCKWAVE_RADIUS;
        for (LivingEntity other : level.getEntitiesOfClass(LivingEntity.class,
                target.getBoundingBox().inflate(radius, 1.0D, radius))) {
            if (isAffected(player, target, other) && other.distanceToSqr(target) <= radius * radius) {
                other.hurtServer(level, player.damageSources().playerAttack(player), SHOCKWAVE_DAMAGE);
                // Push away from the target (knockback() pushes against the given direction)
                other.knockback(SHOCKWAVE_KNOCKBACK, target.getX() - other.getX(), target.getZ() - other.getZ());
            }
        }

        BlockState ground = level.getBlockState(target.blockPosition().below());
        if (!ground.isAir()) {
            level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, ground),
                    target.getX(), target.getY() + 0.1D, target.getZ(), 60, radius / 2, 0.1D, radius / 2, 0.15D);
        }
        level.sendParticles(ParticleTypes.POOF, target.getX(), target.getY() + 0.2D, target.getZ(),
                12, radius / 3, 0.1D, radius / 3, 0.05D);
        level.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 0.5F, 0.6F);
    }

    /** Same targets as the vanilla sweep, plus: no armor stands, no own pets, no players that may not be harmed. */
    private static boolean isAffected(Player player, LivingEntity target, LivingEntity other) {
        if (other == player || other == target || !other.isAlive() || other instanceof ArmorStand) {
            return false;
        }
        if (player.isAlliedTo(other) || other.isAlliedTo(player)) {
            return false;
        }
        if (other instanceof OwnableEntity pet && pet.getOwnerReference() != null
                && player.getUUID().equals(pet.getOwnerReference().getUUID())) {
            return false;
        }
        // PvP disabled on the server, or same team without friendly fire
        return !(other instanceof Player otherPlayer) || player.canHarmPlayer(otherPlayer);
    }
}
