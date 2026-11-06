package net.petemc.vikingarmorweapons.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.armor.ModArmorMaterials;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VikingArmorWeapons.MOD_ID);

    // Since 1.21.2 armor is a normal Item with the armor components (Properties.humanoidArmor), there is no ArmorItem anymore.
    // The 3D model is swapped in client-side (client.VikingArmorClientExtensions).
    // registerItem sets the item id on the properties (Properties.setId) before the item is created.
    public static final DeferredItem<Item> VIKING_HELMET = ITEMS.registerItem("viking_helmet", Item::new, () -> armor(ArmorType.HELMET));
    public static final DeferredItem<Item> VIKING_CHESTPLATE = ITEMS.registerItem("viking_chestplate", Item::new, () -> armor(ArmorType.CHESTPLATE));
    public static final DeferredItem<Item> VIKING_LEGGINGS = ITEMS.registerItem("viking_leggings", Item::new, () -> armor(ArmorType.LEGGINGS));
    public static final DeferredItem<Item> VIKING_BOOTS = ITEMS.registerItem("viking_boots", Item::new, () -> armor(ArmorType.BOOTS));

    public static final DeferredItem<Item> VIKING_BATTLE_AXE = ITEMS.registerItem("viking_battle_axe", VikingBattleAxe::new);
    //public static final DeferredItem<Item> VIKING_LIGHT_AXE = ITEMS.registerItem("viking_light_axe", VikingLightAxe::new);
    public static final DeferredItem<Item> VIKING_HAMMER = ITEMS.registerItem("viking_hammer", VikingHammer::new, VikingHammer::properties);
    public static final DeferredItem<Item> THROWING_AXE = ITEMS.registerItem("throwing_axe", ThrowingAxe::new);
    public static final DeferredItem<Item> THROWING_AXE_BROKEN = ITEMS.registerItem("viking_light_axe_broken", VikingLightAxeBroken::new);

    private static Item.Properties armor(ArmorType type) {
        return new Item.Properties().humanoidArmor(ModArmorMaterials.VIKING, type).fireResistant();
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
