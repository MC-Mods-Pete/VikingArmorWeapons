package net.petemc.vikingarmorweapons.client.model;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;

@EventBusSubscriber(modid = VikingArmorWeapons.MOD_ID, value = Dist.CLIENT)
public class ModModels {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VikingArmorModel.LAYER_LOCATION, VikingArmorModel::createBodyLayer);
    }
}
