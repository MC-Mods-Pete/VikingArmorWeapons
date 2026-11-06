package net.petemc.vikingarmorweapons.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.items.WeaponAbilities;

/** Game-bus events for the weapon abilities (Battle Axe cleave, Hammer shockwave). */
@EventBusSubscriber(modid = VikingArmorWeapons.MOD_ID)
public class CombatEvents {
    // Fired at the start of Player.attack, before the attack cooldown is reset
    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        WeaponAbilities.captureAttack(event.getEntity());
    }
}
