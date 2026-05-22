package net.petemc.vikingarmorweapons.items;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

public class VikingHammer extends AxeItem {
        public VikingHammer() {
            super(new Tier() {
                @Override
                public int getUses() {
                    return 700;
                }

                @Override
                public float getSpeed() {
                    return 6.0F;
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
                    return 15;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(new ItemStack[]{new ItemStack(Items.IRON_INGOT), new ItemStack(Items.FLINT)});
                }
            }, 1.0F, -3.75F, (new Properties()).fireResistant());
        }

    public VikingHammer(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
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

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pSlot) {
        if (pSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(pSlot));
            builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(
                    java.util.UUID.fromString("9ad7f4b2-1c3e-4f5a-8d6b-7e2a1f3c5d4e"),
                    "Knockback modifier", 2.0, AttributeModifier.Operation.ADDITION));
            return builder.build();
        }
        return super.getDefaultAttributeModifiers(pSlot);
    }
}
