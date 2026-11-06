package net.petemc.vikingarmorweapons.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import java.util.List;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class VikingBattleAxe extends AxeItem {
    static final Tier TIER = new Tier() {
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

        // Replaces getLevel() == 1 (stone level) from 1.20.1
        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_STONE_TOOL;
        }

        @Override
        public int getEnchantmentValue() {
            return 30;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.IRON_INGOT, Items.FLINT);
        }
    };

    public VikingBattleAxe() {
        super(TIER, (new Item.Properties()).fireResistant().attributes(AxeItem.createAttributes(TIER, 1.0F, -3.3F)));
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
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".tooltip").withStyle(ChatFormatting.GRAY));
    }
}
