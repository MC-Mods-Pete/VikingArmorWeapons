package net.petemc.vikingarmorweapons.items;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;

public class ModItems {
    public static final Item VIKING_HELMET = register("viking_helmet", new VikingArmor.Helmet());
    public static final Item VIKING_CHESTPLATE = register("viking_chestplate", new VikingArmor.Chestplate());
    public static final Item VIKING_LEGGINGS = register("viking_leggings", new VikingArmor.Leggings());
    public static final Item VIKING_BOOTS = register("viking_boots", new VikingArmor.Boots());

    public static final Item VIKING_BATTLE_AXE = register("viking_battle_axe", new VikingBattleAxe());
    //public static final Item VIKING_LIGHT_AXE = register("viking_light_axe", new VikingLightAxe());
    public static final Item VIKING_HAMMER = register("viking_hammer", new VikingHammer());
    public static final Item THROWING_AXE = register("throwing_axe", new ThrowingAxe());
    public static final Item THROWING_AXE_BROKEN = register("viking_light_axe_broken", new VikingLightAxeBroken());

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, VikingArmorWeapons.id(name), item);
    }

    /** Loads the class, which registers all items (called from the mod initializer). */
    public static void register() {
    }
}
