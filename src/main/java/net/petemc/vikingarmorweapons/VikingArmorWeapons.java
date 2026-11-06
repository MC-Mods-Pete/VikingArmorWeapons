package net.petemc.vikingarmorweapons;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.world.InteractionResult;
import net.petemc.vikingarmorweapons.items.WeaponAbilities;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.items.ModItems;
import org.slf4j.Logger;

public class VikingArmorWeapons implements ModInitializer
{
    public static final String MOD_ID = "vikingarmorweapons";
    public static final String NAME = "Viking Armor and Weapons";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static CreativeModeTab TAB;

    // Minecraft 26.1 renamed ResourceLocation to Identifier
    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitialize()
    {
        ModItems.register();
        ModEntities.register();

        TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, id("vikingarmorweapons"),
                FabricCreativeModeTab.builder()
                        .title(Component.translatable("itemGroup." + MOD_ID))
                        .icon(() -> new ItemStack(ModItems.VIKING_HELMET))
                        .displayItems((parameters, output) -> {
                            output.accept(ModItems.VIKING_HELMET);
                            output.accept(ModItems.VIKING_CHESTPLATE);
                            output.accept(ModItems.VIKING_LEGGINGS);
                            output.accept(ModItems.VIKING_BOOTS);
                            output.accept(ModItems.VIKING_BATTLE_AXE);
                            output.accept(ModItems.VIKING_HAMMER);
                            output.accept(ModItems.THROWING_AXE);
                            output.accept(ModItems.THROWING_AXE_BROKEN);
                        })
                        .build());

        // Battle Axe cleave / Hammer shockwave need the attack state before vanilla resets the attack cooldown
        AttackEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
            WeaponAbilities.captureAttack(player);
            return InteractionResult.PASS;
        });

        ServerLifecycleEvents.SERVER_STARTING.register(server -> LOGGER.info("Starting the mod " + NAME));
    }
}
