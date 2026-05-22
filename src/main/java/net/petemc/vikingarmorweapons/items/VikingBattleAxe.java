package net.petemc.vikingarmorweapons.items;

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
                    return 10.0F;
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
            }, 1.0F, -3.75F, (new Item.Properties()).fireResistant());
        }

    public VikingBattleAxe(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    public boolean hasCraftingRemainingItem(ItemStack stack) {
            return true;
        }

        public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
            return new ItemStack(this);
        }

        public boolean isRepairable(ItemStack itemstack) {
            return false;
        }
    }
