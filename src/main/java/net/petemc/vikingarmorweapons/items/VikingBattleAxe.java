package net.petemc.vikingarmorweapons.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class VikingBattleAxe extends AxeItem {
        public VikingBattleAxe() {
            super(new Tier() {
                @Override
                public int getUses() {
                    return 700;
                }

                @Override
                public float getSpeed() {
                    return 2.0F;
                }

                @Override
                public float getAttackDamageBonus() {
                    return 8.0F;
                }

                @Override
                public int getLevel() {
                    return 1;
                }

                @Override
                public int getEnchantmentValue() {
                    return 30;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(new ItemStack[]{new ItemStack(Items.IRON_INGOT), new ItemStack(Items.FLINT)});
                }
            }, 1.0F, -3.3F, (new Item.Properties()).fireResistant());
        }

    public VikingBattleAxe(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    // Fully charged hits also strike enemies next to the target (see WeaponAbilities)
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            WeaponAbilities.cleave(player, target);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable(this.getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY));
    }
}
