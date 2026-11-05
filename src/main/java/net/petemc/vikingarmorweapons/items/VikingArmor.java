package net.petemc.vikingarmorweapons.items;

import java.util.function.Consumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.petemc.vikingarmorweapons.armor.ModArmorMaterials;
import net.petemc.vikingarmorweapons.client.model.VikingArmorModels;
import org.jetbrains.annotations.NotNull;

public abstract class VikingArmor extends ArmorItem {
    private static final String ARMOR_TEXTURE = "vikingarmorweapons:textures/entities/viking_armor.png";
    private static final String BOOTS_TEXTURE = "vikingarmorweapons:textures/models/armor/viking_armor__layer_1.png";

    public VikingArmor(ArmorItem.Type type, Properties pProperties) {
        super(ModArmorMaterials.VIKING, type, pProperties);
    }

    // Only called on the client (Forge skips it on dedicated servers), so the client classes
    // referenced in here are never loaded server-side.
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                return VikingArmorModels.get(slot, living, defaultModel);
            }
        });
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return slot == EquipmentSlot.FEET ? BOOTS_TEXTURE : ARMOR_TEXTURE;
    }

    public static class Helmet extends VikingArmor {
        public Helmet() {
            super(ArmorItem.Type.HELMET, (new Item.Properties()).fireResistant());
        }
    }

    public static class Chestplate extends VikingArmor {
        public Chestplate() {
            super(ArmorItem.Type.CHESTPLATE, (new Item.Properties()).fireResistant());
        }
    }

    public static class Leggings extends VikingArmor {
        public Leggings() {
            super(ArmorItem.Type.LEGGINGS, (new Item.Properties()).fireResistant());
        }
    }

    public static class Boots extends VikingArmor {
        public Boots() {
            super(ArmorItem.Type.BOOTS, (new Item.Properties()).fireResistant());
        }
    }
}
