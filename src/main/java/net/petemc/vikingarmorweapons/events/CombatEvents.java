package net.petemc.vikingarmorweapons.events;

import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.petemc.vikingarmorweapons.VikingArmorWeapons;
import net.petemc.vikingarmorweapons.items.WeaponAbilities;

/** Game-bus events for the weapon abilities (Battle Axe cleave, Hammer shockwave). */
@Mod.EventBusSubscriber(modid = VikingArmorWeapons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CombatEvents {
    // Fired at the start of Player.attack, before the attack cooldown is reset
    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        WeaponAbilities.captureAttack(event.getEntity());
    }
}
