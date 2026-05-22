package net.petemc.vikingarmorweapons.items;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.ForgeRegistries;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.armor.ModArmorMaterials;
import net.petemc.vikingarmorweapons.client.model.VikingArmorModel;

public abstract class VikingArmor extends ArmorItem {
    public VikingArmor(EquipmentSlot pSlot, Properties pProperties) {
        super(ModArmorMaterials.VIKING, pSlot, pProperties);
    }

    public static class Helmet extends VikingArmor {
        public Helmet() {
            super(EquipmentSlot.HEAD, (new Item.Properties()).tab(VikingArmorWeapons.TAB).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), 
                            Map.of("head", (new VikingArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION))).helmet, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }

        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "vikingarmorweapons:textures/entities/viking_armor.png";
        }
    }

    public static class Chestplate extends VikingArmor {
        public Chestplate() {
            super(EquipmentSlot.CHEST, (new Item.Properties()).tab(VikingArmorWeapons.TAB).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                            Map.of("body", (new VikingArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION))).chestplate, "left_arm", (new VikingArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION))).left_arm, "right_arm", (new VikingArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION))).right_arm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }

        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "vikingarmorweapons:textures/entities/viking_armor.png";
        }
    }

    public static class Leggings extends VikingArmor {
        public Leggings() {
            super(EquipmentSlot.LEGS, (new Item.Properties()).tab(VikingArmorWeapons.TAB).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                            Map.of("left_leg", (new VikingArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION))).legginsleft, "right_leg", (new VikingArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION))).legginsright, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }

        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "vikingarmorweapons:textures/entities/viking_armor.png";
        }
    }

    public static class Boots extends VikingArmor {
        public Boots() {
            super(EquipmentSlot.FEET, (new Item.Properties()).tab(VikingArmorWeapons.TAB).fireResistant());
        }

        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @OnlyIn(Dist.CLIENT)
                public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
                            Map.of("left_leg", (new VikingArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION))).left_leg, "right_leg", (new VikingArmorModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(VikingArmorModel.LAYER_LOCATION))).right_leg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }

        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "vikingarmorweapons:textures/models/armor/viking_armor__layer_1.png";
        }
    }
}

