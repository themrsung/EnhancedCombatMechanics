package jbs.enhancedcombatmechanics.listerners;

import jbs.enhancedcombatmechanics.EnhancedCombatMechanics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public final class NuclearWeaponsHandler implements Listener {
    public NuclearWeaponsHandler(EnhancedCombatMechanics plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    private final EnhancedCombatMechanics plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onExplosion(EntityExplodeEvent e) {
        if (e.isCancelled()) return;

        if (!(e.getEntityType() == EntityType.PRIMED_TNT || e.getEntityType() == EntityType.MINECART_TNT)) return;

        Location location = e.getLocation();
        World world = location.getWorld();

        if (world == null) return;

        world.createExplosion(location, 175);
        e.setCancelled(true);
    }
}
