package net.petemc.vikingarmorweapons;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.items.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VikingArmorWeapons.MOD_ID)
public class VikingArmorWeapons
{
    public static final String MOD_ID = "vikingarmorweapons";
    public static final String NAME = "Viking Armor and Weapons";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("vikingarmorweapons",
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

    public VikingArmorWeapons(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        CREATIVE_MODE_TABS.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        //context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("Starting the mod " + NAME);
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
        }
    }
}
