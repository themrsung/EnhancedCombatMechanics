package jbs.enhancedcombatmechanics.listerners;

import jbs.enhancedcombatmechanics.EnhancedCombatMechanics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public final class MeleeCombatListener implements Listener {
    public MeleeCombatListener(EnhancedCombatMechanics plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    final EnhancedCombatMechanics plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            switch (attacker.getInventory().getItemInMainHand().getType()) {
                case GOLDEN_SWORD:
                    e.setDamage(e.getDamage() * 10);
                    break;
                case NETHERITE_SWORD:
                case DIAMOND_SWORD:
                case IRON_SWORD:
                case STONE_SWORD:
                case WOODEN_SWORD:
                    e.setDamage(e.getDamage() * 2);
                    break;
                case GOLDEN_AXE:
                    e.setDamage(e.getDamage() * 2.5);
                    break;
                case NETHERITE_AXE:
                case DIAMOND_AXE:
                case IRON_AXE:
                case STONE_AXE:
                case WOODEN_AXE:
                    e.setDamage(e.getDamage() * 1.25);
                    break;
                case TRIDENT:
                    e.setDamage(e.getDamage() * 3);
                case SHIELD:
                    e.setDamage(e.getDamage() * 10);
                case GOLDEN_PICKAXE:
                case GOLDEN_SHOVEL:
                case GOLDEN_HOE:
                    e.setDamage(e.getDamage() * 2);
                    break;
                case NETHERITE_PICKAXE:
                case NETHERITE_SHOVEL:
                case NETHERITE_HOE:
                case DIAMOND_PICKAXE:
                case DIAMOND_SHOVEL:
                case DIAMOND_HOE:
                case IRON_PICKAXE:
                case IRON_SHOVEL:
                case IRON_HOE:
                case STONE_PICKAXE:
                case STONE_SHOVEL:
                case STONE_HOE:
                case WOODEN_PICKAXE:
                case WOODEN_SHOVEL:
                case WOODEN_HOE:
                    e.setDamage(e.getDamage() * 1.1);
                    break;
                default:
                    e.setDamage(e.getDamage() * 0.25);
            }
        }

        if (e.getEntity() instanceof Player) {
            Player victim = (Player) e.getEntity();

            ItemStack mainHand = victim.getInventory().getItemInMainHand();
            ItemStack offHand = victim.getInventory().getItemInOffHand();

            if (mainHand.getType() == Material.SHIELD) {
                e.setDamage(e.getDamage() * 0.5);
            }

            if (offHand.getType() == Material.SHIELD) {
                e.setDamage(e.getDamage() * 0.5);
            }
        }
    }
}
