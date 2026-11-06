package net.petemc.vikingarmorweapons.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.armor.ModArmorMaterials;

import java.util.function.Function;

public class ModItems {
    // Since 1.21.2 armor is a normal Item with the armor components (Properties.humanoidArmor), there is no ArmorItem anymore.
    // The 3D model is rendered client-side by client.renderer.VikingArmorRenderer (Fabric ArmorRenderer).
    public static final Item VIKING_HELMET = register("viking_helmet", Item::new, armor(ArmorType.HELMET));
    public static final Item VIKING_CHESTPLATE = register("viking_chestplate", Item::new, armor(ArmorType.CHESTPLATE));
    public static final Item VIKING_LEGGINGS = register("viking_leggings", Item::new, armor(ArmorType.LEGGINGS));
    public static final Item VIKING_BOOTS = register("viking_boots", Item::new, armor(ArmorType.BOOTS));

    public static final Item VIKING_BATTLE_AXE = register("viking_battle_axe", VikingBattleAxe::new, new Item.Properties());
    //public static final Item VIKING_LIGHT_AXE = register("viking_light_axe", VikingLightAxe::new, new Item.Properties());
    public static final Item VIKING_HAMMER = register("viking_hammer", VikingHammer::new, VikingHammer.properties());
    public static final Item THROWING_AXE = register("throwing_axe", ThrowingAxe::new, new Item.Properties());
    public static final Item THROWING_AXE_BROKEN = register("viking_light_axe_broken", VikingLightAxeBroken::new, new Item.Properties());

    private static Item.Properties armor(ArmorType type) {
        return new Item.Properties().humanoidArmor(ModArmorMaterials.VIKING, type).fireResistant();
    }

    /** Since 1.21.2 the item id must be known before the item is created (Properties.setId), like vanilla's Items.registerItem. */
    private static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, VikingArmorWeapons.id(name));
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(properties.setId(key)));
    }

    /** Loads the class, which registers all items (called from the mod initializer). */
    public static void register() {
    }
}
