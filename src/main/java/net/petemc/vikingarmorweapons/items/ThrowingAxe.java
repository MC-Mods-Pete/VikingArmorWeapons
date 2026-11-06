package net.petemc.vikingarmorweapons.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.entity.ThrowableAxeEntity;

public class ThrowingAxe extends Item {

    public ThrowingAxe() {
        super((new Item.Properties()).stacksTo(8).attributes(ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 2.0, Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -3.3, Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build()));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return 300;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (!pAttacker.level().isClientSide() && pAttacker instanceof Player player) {
            if (pAttacker.getRandom().nextFloat() < 0.30f) {
                // 30% chance: axe breaks
                pAttacker.level().playSound(null,
                        pAttacker.getX(), pAttacker.getY(), pAttacker.getZ(),
                        SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);

                if (!player.getAbilities().instabuild) {
                    pStack.shrink(1);
                }
                ItemStack broken = new ItemStack(ModItems.THROWING_AXE_BROKEN);
                if (!player.getInventory().add(broken)) {
                    player.drop(broken, false);
                }
            }
        }
        return true;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeLeft) {
        if (!(pLivingEntity instanceof Player player)) return;

        int charge = this.getUseDuration(pStack, pLivingEntity) - pTimeLeft;
        if (charge < 10) return;

        if (!pLevel.isClientSide()) {
            ThrowableAxeEntity axe = new ThrowableAxeEntity(
                    ModEntities.THROWABLE_AXE, player, pLevel);
            axe.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.9F, 1.0F);
            // Damage = ceil(velocity * baseDamage): 4.0 * 1.9 = 7.6 -> 8 at launch (thrown trident level).
            // Before 1.0.3: 5.5 base + 1 HP via setHealth (bypassed death handling: no loot/XP/death message).
            axe.setBaseDamage(4.0);
            axe.setKnockback(1);
            axe.setSilent(true);

            axe.pickup = AbstractArrow.Pickup.ALLOWED;
            if (!player.getAbilities().instabuild) {
                pStack.shrink(1);
            }

            pLevel.addFreshEntity(axe);
            pLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}
