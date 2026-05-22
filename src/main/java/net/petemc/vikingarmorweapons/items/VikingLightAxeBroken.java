package net.petemc.vikingarmorweapons.items;

import net.minecraft.world.item.Item;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;

public class VikingLightAxeBroken extends Item {

    public VikingLightAxeBroken() {
        super(new Properties()
                .tab(VikingArmorWeapons.TAB)
                .stacksTo(8)
        );
    }
}

