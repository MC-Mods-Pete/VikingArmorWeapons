package net.petemc.vikingarmorweapons.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.entity.ThrowableAxeEntity;

public class ThrowingAxe extends Item {

    public ThrowingAxe() {
        super((new Item.Properties()).tab(VikingArmorWeapons.TAB).stacksTo(8));
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
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pSlot) {
        if (pSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(pSlot));
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 2.0, Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -3.3, Operation.ADDITION));
            return builder.build();
        }
        return super.getDefaultAttributeModifiers(pSlot);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (!pAttacker.level.isClientSide() && pAttacker instanceof Player player) {
            VikingArmorWeapons.LOGGER.info("Throwing Axe hit an entity!");
            if (pAttacker.getRandom().nextFloat() < 0.50f) {
                VikingArmorWeapons.LOGGER.info("Throwing Axe broke on hit!");
                // 10% Chance: Axt zerbricht
                pAttacker.level.playSound(null,
                        pAttacker.getX(), pAttacker.getY(), pAttacker.getZ(),
                        SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);

                if (!player.getAbilities().instabuild) {
                    pStack.shrink(1);
                }
                ItemStack broken = new ItemStack(ModItems.THROWING_AXE_BROKEN.get());
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

        int charge = this.getUseDuration(pStack) - pTimeLeft;
        if (charge < 10) return;

        if (!pLevel.isClientSide()) {
            ThrowableAxeEntity axe = new ThrowableAxeEntity(
                    ModEntities.THROWABLE_AXE.get(), player, pLevel);
            axe.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.9F, 1.0F);
            axe.setBaseDamage(5.5);
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
