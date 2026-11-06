package net.petemc.vikingarmorweapons.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.petemc.vikingarmorweapons.client.model.VikingArmorModel;
import net.petemc.vikingarmorweapons.client.renderer.ThrowableAxeRenderer;
import net.petemc.vikingarmorweapons.client.renderer.VikingArmorRenderer;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.items.ModItems;

/**
 * Client entrypoint (replaces the Forge client event subscribers ModModels and ModEvents).
 * The flat inventory icons of battle axe, hammer and broken throwing axe no longer need code: since 1.21.4 the item
 * model definitions (assets/vikingarmorweapons/items/*.json) pick the "_gui" model for the GUI display context.
 */
public class VikingArmorWeaponsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(VikingArmorModel.LAYER_LOCATION, VikingArmorModel::createBodyLayer);
        // Vanilla registry, made public by Fabric (EntityRendererRegistry is deprecated)
        EntityRenderers.register(ModEntities.THROWABLE_AXE, ThrowableAxeRenderer::new);
        ArmorRenderer.register(VikingArmorRenderer::new,
                ModItems.VIKING_HELMET, ModItems.VIKING_CHESTPLATE, ModItems.VIKING_LEGGINGS, ModItems.VIKING_BOOTS);
    }
}
