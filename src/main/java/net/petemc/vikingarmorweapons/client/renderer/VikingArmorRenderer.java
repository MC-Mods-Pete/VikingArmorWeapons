package net.petemc.vikingarmorweapons.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.client.model.VikingArmorModels;

/**
 * Renders the Viking armor with its own 3D model (Fabric replacement for Forge's IClientItemExtensions
 * getHumanoidArmorModel + ArmorItem.getArmorTexture).
 */
public class VikingArmorRenderer implements ArmorRenderer {
    private static final ResourceLocation ARMOR_TEXTURE = VikingArmorWeapons.id("textures/entities/viking_armor.png");
    private static final ResourceLocation BOOTS_TEXTURE = VikingArmorWeapons.id("textures/models/armor/viking_armor__layer_1.png");

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, ItemStack stack, LivingEntity entity,
                       EquipmentSlot slot, int light, HumanoidModel<LivingEntity> contextModel) {
        HumanoidModel<LivingEntity> model = VikingArmorModels.get(slot);
        // Pose, crouching, riding, baby etc. of the wearer
        contextModel.copyPropertiesTo(model);
        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model,
                slot == EquipmentSlot.FEET ? BOOTS_TEXTURE : ARMOR_TEXTURE);
    }
}
