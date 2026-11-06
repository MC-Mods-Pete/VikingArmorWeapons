package net.petemc.vikingarmorweapons.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.client.VikingArmorClientExtensions;
import net.petemc.vikingarmorweapons.client.renderer.ThrowableAxeRenderer;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.items.ModItems;

/** Client mod-bus events. The flat inventory icons need no code: the item model definitions (assets/.../items/*.json) pick the "_gui" model. */
@EventBusSubscriber(modid = VikingArmorWeapons.MOD_ID, value = Dist.CLIENT)
public class ModEvents {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.THROWABLE_AXE.get(), ThrowableAxeRenderer::new);
    }

    // Fired on every resource reload after the model layers were baked
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        VikingArmorClientExtensions.bakeModels(event.getEntityModels());
    }

    // The 3D armor model and texture for all four Viking armor pieces
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(VikingArmorClientExtensions.INSTANCE,
                ModItems.VIKING_HELMET.get(), ModItems.VIKING_CHESTPLATE.get(), ModItems.VIKING_LEGGINGS.get(), ModItems.VIKING_BOOTS.get());
    }
}
