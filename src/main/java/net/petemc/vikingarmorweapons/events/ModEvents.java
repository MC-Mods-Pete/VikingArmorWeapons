package net.petemc.vikingarmorweapons.events;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.client.model.VikingArmorModels;
import net.petemc.vikingarmorweapons.client.renderer.ThrowableAxeRenderer;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.items.ModItems;

// bus = MOD stays on purpose: older NeoForge 21.1 builds don't detect the bus automatically
@SuppressWarnings("removal")
@EventBusSubscriber(modid = VikingArmorWeapons.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.THROWABLE_AXE.get(), ThrowableAxeRenderer::new);
    }

    // Replaces Item.initializeClient (removed in NeoForge 1.21): the 3D armor model for all four Viking armor pieces
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                return VikingArmorModels.get(slot, living, defaultModel);
            }
        }, ModItems.VIKING_HELMET.get(), ModItems.VIKING_CHESTPLATE.get(), ModItems.VIKING_LEGGINGS.get(), ModItems.VIKING_BOOTS.get());
    }
}
