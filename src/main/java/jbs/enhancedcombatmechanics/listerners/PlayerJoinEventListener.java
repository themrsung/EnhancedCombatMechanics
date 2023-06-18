package jbs.enhancedcombatmechanics.listerners;

import jbs.enhancedcombatmechanics.EnhancedCombatMechanics;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerJoinEventListener implements Listener {
    public PlayerJoinEventListener(EnhancedCombatMechanics plugin) {
        this.plugin = plugin;
    }
    final EnhancedCombatMechanics plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Objects.requireNonNull(e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(40);
    }
}
