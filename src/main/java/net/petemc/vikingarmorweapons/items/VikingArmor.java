package net.petemc.vikingarmorweapons.items;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.petemc.vikingarmorweapons.armor.ModArmorMaterials;

public abstract class VikingArmor extends ArmorItem {
    // The 3D model and textures are rendered client-side by client.renderer.VikingArmorRenderer (Fabric ArmorRenderer)
    public VikingArmor(ArmorItem.Type type, Properties pProperties) {
        super(ModArmorMaterials.VIKING, type, pProperties.durability(type.getDurability(ModArmorMaterials.DURABILITY_MULTIPLIER)));
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
