package net.petemc.vikingarmorweapons.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;

import java.util.function.Consumer;

public class VikingBattleAxe extends AxeItem {
    /** Repair materials for the anvil: iron ingot, flint (data/vikingarmorweapons/tags/item/repairs_viking_weapons.json) */
    public static final TagKey<Item> REPAIRS_VIKING_WEAPONS = TagKey.create(Registries.ITEM, VikingArmorWeapons.id("repairs_viking_weapons"));

    // Same values as the older versions: 700 uses, mining speed 2, +8 damage, stone mining level, enchantability 30
    static final ToolMaterial MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 700, 2.0F, 8.0F, 30, REPAIRS_VIKING_WEAPONS);

    // 1 (player) + 1 + 8 = 10 damage, 4 - 3.3 = 0.7 attack speed
    public VikingBattleAxe(Item.Properties properties) {
        super(MATERIAL, 1.0F, -3.3F, properties.fireResistant());
    }

    // Fully charged hits also strike enemies next to the target (see WeaponAbilities)
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            WeaponAbilities.cleave(player, target);
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
