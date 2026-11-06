package net.petemc.vikingarmorweapons.armor;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;

import java.util.EnumMap;
import java.util.Map;

/**
 * Since 1.21.2 armor materials are plain records again (no registry entry). Same values as the older versions:
 * durability {11, 16, 15, 13} x 20 = Helmet 220, Chest 320, Legs 300, Boots 260, protection 2/5/6/2 (iron level),
 * toughness 1.0, knockback resistance 0.1, enchantability 10.
 */
public class ModArmorMaterials {
    public static final int DURABILITY_MULTIPLIER = 20;

    /** Repair materials for the anvil: leather, raw iron, iron ingot (data/vikingarmorweapons/tags/item/repairs_viking_armor.json) */
    public static final TagKey<Item> REPAIRS_VIKING_ARMOR = TagKey.create(Registries.ITEM, VikingArmorWeapons.id("repairs_viking_armor"));

    /**
     * The armor is rendered with its own 3D model by client.renderer.VikingArmorRenderer, so there is no
     * equipment asset file (assets/vikingarmorweapons/equipment/viking.json). Vanilla treats a missing asset as "no layers".
     */
    public static final ResourceKey<EquipmentAsset> VIKING_ASSET = ResourceKey.create(EquipmentAssets.ROOT_ID, VikingArmorWeapons.id("viking"));

    public static final ArmorMaterial VIKING = new ArmorMaterial(
            DURABILITY_MULTIPLIER,
            defense(2, 5, 6, 2),
            10,
            SoundEvents.ARMOR_EQUIP_LEATHER,
            1.0F, 0.1F,
            REPAIRS_VIKING_ARMOR,
            VIKING_ASSET);

    private static Map<ArmorType, Integer> defense(int boots, int leggings, int chestplate, int helmet) {
        Map<ArmorType, Integer> map = new EnumMap<>(ArmorType.class);
        map.put(ArmorType.BOOTS, boots);
        map.put(ArmorType.LEGGINGS, leggings);
        map.put(ArmorType.CHESTPLATE, chestplate);
        map.put(ArmorType.HELMET, helmet);
        return map;
    }
}
