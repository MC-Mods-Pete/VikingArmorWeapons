package net.petemc.vikingarmorweapons.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;

public class ModEntities {
    // Same values as the Forge version (setShouldReceiveVelocityUpdates(true) / setTrackingRange(64) / setUpdateInterval(1))
    public static final EntityType<ThrowableAxeEntity> THROWABLE_AXE = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            VikingArmorWeapons.id("projectile_throwable_axe"),
            FabricEntityTypeBuilder.<ThrowableAxeEntity>create(MobCategory.MISC, ThrowableAxeEntity::new)
                    .forceTrackedVelocityUpdates(true)
                    .trackRangeChunks(64)
                    .trackedUpdateRate(1)
                    .disableSummon()
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                    .build());

    /** Loads the class, which registers all entity types (called from the mod initializer). */
    public static void register() {
    }
}
