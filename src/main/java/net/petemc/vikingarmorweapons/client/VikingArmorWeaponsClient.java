package net.petemc.vikingarmorweapons.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.petemc.vikingarmorweapons.client.model.VikingArmorModel;
import net.petemc.vikingarmorweapons.client.renderer.ThrowableAxeRenderer;
import net.petemc.vikingarmorweapons.client.renderer.VikingArmorRenderer;
import net.petemc.vikingarmorweapons.entity.ModEntities;
import net.petemc.vikingarmorweapons.items.ModItems;

/** Client entrypoint (replaces the Forge client event subscribers ModModels and ModEvents). */
public class VikingArmorWeaponsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(VikingArmorModel.LAYER_LOCATION, VikingArmorModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntities.THROWABLE_AXE, ThrowableAxeRenderer::new);
        ArmorRenderer.register(new VikingArmorRenderer(),
                ModItems.VIKING_HELMET, ModItems.VIKING_CHESTPLATE, ModItems.VIKING_LEGGINGS, ModItems.VIKING_BOOTS);
        GuiItemModels.register();
    }
}
