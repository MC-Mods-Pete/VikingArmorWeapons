package net.petemc.vikingarmorweapons.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.client.model.VikingArmorModels;

/**
 * Renders the Viking armor with its own 3D model (Fabric replacement for Forge's IClientItemExtensions
 * getHumanoidArmorModel + ArmorItem.getArmorTexture). Since 1.21.9 models are submitted and drawn later.
 */
public class VikingArmorRenderer implements ArmorRenderer {
    private static final Identifier ARMOR_TEXTURE = VikingArmorWeapons.id("textures/entities/viking_armor.png");
    private static final Identifier BOOTS_TEXTURE = VikingArmorWeapons.id("textures/models/armor/viking_armor__layer_1.png");

    private final VikingArmorModels models;

    public VikingArmorRenderer(EntityRendererProvider.Context context) {
        this.models = new VikingArmorModels(context);
    }

    @Override
    public void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, HumanoidRenderState state,
                       EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
        // Same check as vanilla's HumanoidArmorLayer: small armor stands use the scaled adult model, real babies their own model
        boolean babyProportions = state.isBaby && state.entityType != EntityType.ARMOR_STAND;
        VikingArmorModels.Piece model = this.models.create(slot, contextModel, babyProportions);
        Identifier texture = slot == EquipmentSlot.FEET ? BOOTS_TEXTURE : ARMOR_TEXTURE;

        submitNodeCollector.order(1).submitModel(model, state, poseStack, RenderTypes.armorCutoutNoCull(texture),
                light, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
        if (stack.hasFoil()) {
            submitNodeCollector.order(2).submitModel(model, state, poseStack, RenderTypes.armorEntityGlint(),
                    light, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
        }
    }
}
