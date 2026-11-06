package net.petemc.vikingarmorweapons.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Client-only cache of the Viking armor models (one HumanoidModel per armor slot).
 * <p>
 * Sharing one instance is safe: VikingArmorRenderer copies the current pose of the wearer onto the model right
 * before rendering it, just like vanilla shares its armor models.
 * <p>
 * Only referenced from client code (VikingArmorRenderer), never loaded on a dedicated server.
 */
public final class VikingArmorModels {
    private static final String[] HUMANOID_PARTS = {"head", "hat", "body", "right_arm", "left_arm", "right_leg", "left_leg"};
    private static final Map<EquipmentSlot, HumanoidModel<LivingEntity>> CACHE = new EnumMap<>(EquipmentSlot.class);

    private VikingArmorModels() {
    }

    public static HumanoidModel<LivingEntity> get(EquipmentSlot slot) {
        return CACHE.computeIfAbsent(slot, VikingArmorModels::create);
    }

    private static HumanoidModel<LivingEntity> create(EquipmentSlot slot) {
        // Every slot bakes its own copy, so no ModelPart is ever shared between two models
        VikingArmorModel<LivingEntity> viking = new VikingArmorModel<>(
                Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION));

        Map<String, ModelPart> parts = new HashMap<>();
        switch (slot) {
            case HEAD -> parts.put("head", viking.helmet);
            case CHEST -> {
                parts.put("body", viking.chestplate);
                parts.put("left_arm", viking.left_arm);
                parts.put("right_arm", viking.right_arm);
            }
            case LEGS -> {
                parts.put("left_leg", viking.legginsleft);
                parts.put("right_leg", viking.legginsright);
            }
            case FEET -> {
                parts.put("left_leg", viking.left_leg);
                parts.put("right_leg", viking.right_leg);
            }
            default -> {
            }
        }
        // HumanoidModel requires all humanoid parts to exist; unused ones stay empty
        for (String name : HUMANOID_PARTS) {
            parts.putIfAbsent(name, new ModelPart(Collections.emptyList(), Collections.emptyMap()));
        }
        return new HumanoidModel<>(new ModelPart(Collections.emptyList(), parts));
    }
}
