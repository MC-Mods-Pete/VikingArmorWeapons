package net.petemc.vikingarmorweapons;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.petemc.vikingarmorweapons.armor.ModArmorMaterials;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.items.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(VikingArmorWeapons.MOD_ID)
public class VikingArmorWeapons
{
    public static final String MOD_ID = "vikingarmorweapons";
    public static final String NAME = "Viking Armor and Weapons";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("vikingarmorweapons",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MOD_ID))
                    .icon(() -> new ItemStack(ModItems.VIKING_HELMET.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.VIKING_HELMET.get());
                        output.accept(ModItems.VIKING_CHESTPLATE.get());
                        output.accept(ModItems.VIKING_LEGGINGS.get());
                        output.accept(ModItems.VIKING_BOOTS.get());
                        output.accept(ModItems.VIKING_BATTLE_AXE.get());
                        output.accept(ModItems.VIKING_HAMMER.get());
                        output.accept(ModItems.THROWING_AXE.get());
                        output.accept(ModItems.THROWING_AXE_BROKEN.get());
                    })
                    .build());

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public VikingArmorWeapons(IEventBus modEventBus, ModContainer modContainer)
    {
        CREATIVE_MODE_TABS.register(modEventBus);
        ModArmorMaterials.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Starting the mod " + NAME);
    }
}
