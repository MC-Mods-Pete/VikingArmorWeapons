package net.petemc.vikingarmorweapons.client;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.items.ModItems;

import java.util.Map;

/**
 * Flat 2D icon in the inventory, 3D model everywhere else (hand, ground, item frame).
 * Fabric replacement for Forge's "forge:separate_transforms" model loader: the 3D model is the normal
 * item model (models/item/<name>.json), the icon is models/item/<name>_gui.json and is swapped in for
 * ItemDisplayContext.GUI by mixin.client.ItemRendererMixin.
 */
public final class GuiItemModels {
    private static Map<Item, ResourceLocation> guiModels;

    private GuiItemModels() {
    }

    static void register() {
        guiModels = Map.of(
                ModItems.VIKING_BATTLE_AXE, VikingArmorWeapons.id("item/viking_battle_axe_gui"),
                ModItems.VIKING_HAMMER, VikingArmorWeapons.id("item/viking_hammer_gui"),
                ModItems.THROWING_AXE_BROKEN, VikingArmorWeapons.id("item/viking_light_axe_broken_gui"));
        ModelLoadingPlugin.register(context -> context.addModels(guiModels.values()));
    }

    /** The baked GUI model for this item, or null if the item has no separate GUI model. */
    public static BakedModel get(Item item) {
        ResourceLocation id = guiModels == null ? null : guiModels.get(item);
        return id == null ? null : Minecraft.getInstance().getModelManager().getModel(id);
    }
}
