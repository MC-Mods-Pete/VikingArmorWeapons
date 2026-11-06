package net.petemc.vikingarmorweapons.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.armor.ModArmorMaterials;

/** The 3D model is registered client-side in events.ModEvents (RegisterClientExtensionsEvent). */
public abstract class VikingArmor extends ArmorItem {
    private static final ResourceLocation ARMOR_TEXTURE = VikingArmorWeapons.id("textures/entities/viking_armor.png");
    private static final ResourceLocation BOOTS_TEXTURE = VikingArmorWeapons.id("textures/models/armor/viking_armor__layer_1.png");

    public VikingArmor(ArmorItem.Type type, Properties pProperties) {
        super(ModArmorMaterials.VIKING, type, pProperties.durability(type.getDurability(ModArmorMaterials.DURABILITY_MULTIPLIER)));
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return slot == EquipmentSlot.FEET ? BOOTS_TEXTURE : ARMOR_TEXTURE;
    }

    public static class Helmet extends VikingArmor {
        public Helmet() {
            super(ArmorItem.Type.HELMET, (new Item.Properties()).fireResistant());
        }
    }

    public static class Chestplate extends VikingArmor {
        public Chestplate() {
            super(ArmorItem.Type.CHESTPLATE, (new Item.Properties()).fireResistant());
        }
    }

    public static class Leggings extends VikingArmor {
        public Leggings() {
            super(ArmorItem.Type.LEGGINGS, (new Item.Properties()).fireResistant());
        }
    }

    public static class Boots extends VikingArmor {
        public Boots() {
            super(ArmorItem.Type.BOOTS, (new Item.Properties()).fireResistant());
        }
    }
}
