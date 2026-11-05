package net.petemc.vikingarmorweapons.mixin.client;

import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.petemc.vikingarmorweapons.client.GuiItemModels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/** Uses the flat GUI model in the inventory (see GuiItemModels), like vanilla does for the trident and spyglass. */
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "render", at = @At("HEAD"), argsOnly = true)
    private BakedModel vikingarmorweapons$useGuiModel(BakedModel model, ItemStack stack, ItemDisplayContext displayContext) {
        if (displayContext == ItemDisplayContext.GUI) {
            BakedModel guiModel = GuiItemModels.get(stack.getItem());
            if (guiModel != null) {
                return guiModel;
            }
        }
        return model;
    }
}
