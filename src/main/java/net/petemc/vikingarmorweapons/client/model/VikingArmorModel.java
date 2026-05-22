package net.petemc.vikingarmorweapons.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import org.jetbrains.annotations.NotNull;

public class VikingArmorModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(VikingArmorWeapons.MOD_ID, "modelviking_armor"), "main");
    public final ModelPart chestplate;
    public final ModelPart left_leg;
    public final ModelPart right_leg;
    public final ModelPart left_arm;
    public final ModelPart right_arm;
    public final ModelPart helmet;
    public final ModelPart legginsleft;
    public final ModelPart legginsright;
    public final ModelPart seax;
    public final ModelPart boots;

    public VikingArmorModel(ModelPart root) {
        this.chestplate = root.getChild("chestplate");
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");
        this.left_arm = root.getChild("left_arm");
        this.right_arm = root.getChild("right_arm");
        this.helmet = root.getChild("helmet");
        this.legginsleft = root.getChild("legginsleft");
        this.legginsright = root.getChild("legginsright");
        this.seax = root.getChild("seax");
        this.boots = root.getChild("boots");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition chestplate = partdefinition.addOrReplaceChild("chestplate", CubeListBuilder.create().texOffs(0, 47).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(26, 47).addBox(-4.0F, 0.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.21F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition sword = chestplate.addOrReplaceChild("sword", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, 6.0F, 3.5F, 0.0F, 0.0F, -0.6545F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(16, 15).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.21F)), PartPose.offset(2.0F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(16, 15).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));
        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 31).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(4.0F, 2.0F, 0.0F));
        left_arm.addOrReplaceChild("shoulder2", CubeListBuilder.create().texOffs(32, 13).addBox(-1.0F, -1.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 0.2182F));
        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 31).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offset(-4.0F, 2.0F, 0.0F));
        PartDefinition shoulder = right_arm.addOrReplaceChild("shoulder", CubeListBuilder.create(), PartPose.offset(9.0F, -2.0F, 0.0F));
        shoulder.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 13).addBox(-5.0F, -1.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2094F));
        PartDefinition helmet = partdefinition.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(70, 47).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(96, 38).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        helmet.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(52, 50).addBox(-1.0F, -1.5F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(58, 58).addBox(2.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -5.5F, 0.0F, -0.7854F, 0.0F, 0.4363F));
        helmet.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(52, 50).addBox(-4.0F, -1.5F, -1.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(58, 58).addBox(-5.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -5.5F, 0.0F, -0.7854F, 0.0F, -0.4363F));
        PartDefinition legginsleft = partdefinition.addOrReplaceChild("legginsleft", CubeListBuilder.create(), PartPose.offset(-1.0F, 12.0F, 0.0F));
        legginsleft.addOrReplaceChild("side_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-6.8F, -0.5F, -2.5F, 7.0F, 8.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F));
        PartDefinition legginsright = partdefinition.addOrReplaceChild("legginsright", CubeListBuilder.create(), PartPose.offset(-1.0F, 12.0F, 0.0F));
        legginsright.addOrReplaceChild("front_r1", CubeListBuilder.create().texOffs(48, 0).addBox(-3.5F, -0.5F, -3.7F, 7.0F, 6.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, -0.1309F, 0.0F, 0.0F));
        legginsright.addOrReplaceChild("back_r1", CubeListBuilder.create().texOffs(66, 0).addBox(-3.5F, -0.7F, 0.6F, 7.0F, 7.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.1396F, 0.0F, 0.0F));
        PartDefinition seax = partdefinition.addOrReplaceChild("seax", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 13.6F, 0.4F, 0.0F, -0.1309F, 0.0873F));
        seax.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(114, 1).addBox(-2.3F, -0.1F, -7.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.2F)).texOffs(104, 0).addBox(-2.8F, -0.5F, -2.7F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(106, 10).addBox(-1.8F, -0.5F, -1.7F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.0036F, 0.0F, 0.0F));
        PartDefinition boots = partdefinition.addOrReplaceChild("boots", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void setupAnim(T t, float v, float v1, float v2, float v3, float v4) {
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.chestplate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.helmet.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.legginsleft.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.legginsright.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.seax.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.boots.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

