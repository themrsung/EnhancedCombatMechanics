package jbs.enhancedcombatmechanics.listerners;

import jbs.enhancedcombatmechanics.EnhancedCombatMechanics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public final class BodyArmorHandler implements Listener {
    public BodyArmorHandler(EnhancedCombatMechanics plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    final EnhancedCombatMechanics plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            if (!(e.getDamager() instanceof Projectile)) return;

            ItemStack[] armorStack = p.getInventory().getArmorContents();

            for (ItemStack armor : armorStack) {
                if (armor != null) {
                    switch (armor.getType()) {
                        case CHAINMAIL_CHESTPLATE:
                            e.setDamage(1);
                        case GOLDEN_CHESTPLATE:
                            e.setDamage(1);
                        case NETHERITE_CHESTPLATE:
                            e.setDamage(1);
                        case DIAMOND_CHESTPLATE:
                            e.setDamage(e.getDamage() * 0.1);
                        case IRON_CHESTPLATE:
                            e.setDamage(e.getDamage() * 0.25);
                        case LEATHER_CHESTPLATE:
                            e.setDamage(e.getDamage() * 0.5);
                    }

                    ItemMeta meta = armor.getItemMeta();
                    if (meta instanceof Damageable) {
                        Damageable damageable = (Damageable) meta;

                        damageable.setDamage(damageable.getDamage() + 195);

                        armor.setItemMeta(damageable);
                    }
                }
            }
        }
    }
}
