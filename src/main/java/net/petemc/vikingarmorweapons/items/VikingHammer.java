package net.petemc.vikingarmorweapons.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;

import java.util.function.Consumer;

public class VikingHammer extends AxeItem {
    // Same values as the older versions: 700 uses, mining speed 6, +10 damage, stone mining level, enchantability 15
    static final ToolMaterial MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 700, 6.0F, 10.0F, 15, VikingBattleAxe.REPAIRS_VIKING_WEAPONS);

    // 1 (player) + 1 + 10 = 12 damage, 4 - 3.4 = 0.6 attack speed, +1 attack knockback (see properties())
    public VikingHammer(Item.Properties properties) {
        super(MATERIAL, 1.0F, -3.4F, properties.fireResistant());
    }

    /**
     * AxeItem sets the attack damage/speed modifiers itself (Properties.axe -> attributes(...)) after anything set
     * beforehand, so the knockback modifier is appended whenever the attributes are set.
     */
    static Item.Properties properties() {
        return new Item.Properties() {
            @Override
            public Item.Properties attributes(ItemAttributeModifiers attributes) {
                return super.attributes(attributes.withModifierAdded(
                        Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(VikingArmorWeapons.id("hammer_knockback"), 1.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND));
            }
        };
    }

    // Critical hits knock back all enemies around the target (see WeaponAbilities)
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            WeaponAbilities.shockwave(player, target);
        }
        super.hurtEnemy(stack, target, attacker);
    }

    @SuppressWarnings("deprecation") // still called by ItemStack.addDetailsToTooltip in 26.1
    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay display,
                                Consumer<Component> tooltip, TooltipFlag flag) {
        tooltip.accept(Component.translatable(this.getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY));
    }
}
