package jbs.enhancedcombatmechanics.listerners;

import jbs.enhancedcombatmechanics.EnhancedCombatMechanics;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public final class KineticDamageListener implements Listener {
    public KineticDamageListener(EnhancedCombatMechanics plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    final EnhancedCombatMechanics plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setDamage(e.getDamage() * 2);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) {
            e.setDamage(e.getDamage() * 10);
        }
    }
}
