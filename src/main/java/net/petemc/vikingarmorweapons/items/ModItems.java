package net.petemc.vikingarmorweapons.items;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VikingArmorWeapons.MOD_ID);

    public static final RegistryObject<Item> VIKING_HELMET = ITEMS.register("viking_helmet", VikingArmor.Helmet::new);
    public static final RegistryObject<Item> VIKING_CHESTPLATE = ITEMS.register("viking_chestplate", VikingArmor.Chestplate::new);
    public static final RegistryObject<Item> VIKING_LEGGINGS = ITEMS.register("viking_leggings", VikingArmor.Leggings::new);
    public static final RegistryObject<Item> VIKING_BOOTS = ITEMS.register("viking_boots", VikingArmor.Boots::new);

    public static final RegistryObject<Item> VIKING_BATTLE_AXE = ITEMS.register("viking_battle_axe", VikingBattleAxe::new);
    //public static final RegistryObject<Item> VIKING_LIGHT_AXE = ITEMS.register("viking_light_axe", VikingLightAxe::new);
    public static final RegistryObject<Item> VIKING_HAMMER = ITEMS.register("viking_hammer", VikingHammer::new);
    public static final RegistryObject<Item> THROWING_AXE = ITEMS.register("throwing_axe", ThrowingAxe::new);
    public static final RegistryObject<Item> THROWING_AXE_BROKEN = ITEMS.register("viking_light_axe_broken", VikingLightAxeBroken::new);

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
