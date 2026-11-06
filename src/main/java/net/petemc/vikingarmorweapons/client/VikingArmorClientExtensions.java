package net.petemc.vikingarmorweapons.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.client.model.VikingArmorModels;

/**
 * Renders the Viking armor with its own 3D model and texture (replaces Item.initializeClient / ArmorItem.getArmorTexture).
 * Vanilla's armor layer draws the layers of the equipment asset assets/vikingarmorweapons/equipment/viking.json with the
 * model and texture returned here; glint is added by vanilla.
 */
public final class VikingArmorClientExtensions implements IClientItemExtensions {
    public static final VikingArmorClientExtensions INSTANCE = new VikingArmorClientExtensions();

    private static final Identifier ARMOR_TEXTURE = VikingArmorWeapons.id("textures/entities/viking_armor.png");
    private static final Identifier BOOTS_TEXTURE = VikingArmorWeapons.id("textures/models/armor/viking_armor__layer_1.png");

    private static VikingArmorModels models;

    private VikingArmorClientExtensions() {
    }

    /** Model layers are baked again on every resource reload, so the Viking parts are too. */
    public static void bakeModels(EntityModelSet entityModels) {
        models = new VikingArmorModels(entityModels);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Model getGenericArmorModel(ItemStack stack, EquipmentClientInfo.LayerType layerType, Model original) {
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        if (models == null || equippable == null || !(original instanceof HumanoidModel<?> humanoid)) {
            return original;
        }
        // HUMANOID_BABY = real baby mobs with their own model since 26.1 (small armor stands use the scaled adult model)
        return models.create(equippable.slot(), (HumanoidModel<HumanoidRenderState>) humanoid,
                layerType == EquipmentClientInfo.LayerType.HUMANOID_BABY);
    }

    @Override
    public Identifier getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type, EquipmentClientInfo.Layer layer, Identifier _default) {
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        return equippable != null && equippable.slot() == EquipmentSlot.FEET ? BOOTS_TEXTURE : ARMOR_TEXTURE;
    }
}
