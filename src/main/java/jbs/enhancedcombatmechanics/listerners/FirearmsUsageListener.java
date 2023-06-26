package jbs.enhancedcombatmechanics.listerners;

import jbs.enhancedcombatmechanics.EnhancedCombatMechanics;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public final class FirearmsUsageListener implements Listener {
    public FirearmsUsageListener(EnhancedCombatMechanics plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    private final EnhancedCombatMechanics plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFirearmShot(PlayerInteractEvent e) {

        Action action = e.getAction();
        if (!(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) return;

        PlayerInventory inventory = e.getPlayer().getInventory();
        ItemStack firearm = e.getPlayer().getInventory().getItemInMainHand();

        if (!(firearm.getType() == Material.BOW || firearm.getType() == Material.CROSSBOW)) return;



        int ammoIndex = inventory.first(Material.ARROW);
        if (ammoIndex == -1) {
            e.setCancelled(true);
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1, 1);
            return;
        }

        ItemStack ammo = inventory.getItem(ammoIndex);
        assert ammo != null;

        ammo.setAmount(ammo.getAmount() - 1);

        e.setCancelled(true);

        Arrow bullet = e.getPlayer().launchProjectile(Arrow.class);
        bullet.setShooter(e.getPlayer());

        if (firearm.getType() == Material.CROSSBOW) {
            bullet.setDamage(bullet.getDamage() * 3);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onProjectileFired(ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof Arrow) {
            Arrow bullet = (Arrow) e.getEntity();

            bullet.setGravity(false);
            bullet.setDamage(bullet.getDamage() * 3);
            bullet.setVelocity(bullet.getVelocity().multiply(10d));

            Location firedLocation = e.getLocation();
            World world = firedLocation.getWorld();

            assert world != null;
            world.playSound(firedLocation, Sound.ENTITY_GENERIC_EXPLODE, 50, 1);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBulletLanded(ProjectileHitEvent e) {
        if (e.getEntity().getType() != EntityType.ARROW) return;

        e.getEntity().remove();

        Block block = e.getHitBlock();

        if (block != null) {
            if (GLASS.contains(block.getType())) {
                block.setType(Material.AIR);
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_GLASS_BREAK, 10, 1);
            }
        }

    }

    private final static List<Material> GLASS = Arrays.asList(
            Material.GLASS,
            Material.GLASS_PANE,

            Material.BLACK_STAINED_GLASS,
            Material.BLACK_STAINED_GLASS_PANE,

            Material.WHITE_STAINED_GLASS,
            Material.WHITE_STAINED_GLASS_PANE,

            Material.GRAY_STAINED_GLASS,
            Material.GRAY_STAINED_GLASS_PANE,

            Material.LIGHT_GRAY_STAINED_GLASS,
            Material.LIGHT_GRAY_STAINED_GLASS_PANE,

            Material.BROWN_STAINED_GLASS,
            Material.BROWN_STAINED_GLASS_PANE,

            Material.RED_STAINED_GLASS,
            Material.RED_STAINED_GLASS_PANE,

            Material.ORANGE_STAINED_GLASS,
            Material.ORANGE_STAINED_GLASS_PANE,

            Material.YELLOW_STAINED_GLASS,
            Material.YELLOW_STAINED_GLASS_PANE,

            Material.LIME_STAINED_GLASS,
            Material.LIME_STAINED_GLASS_PANE,

            Material.GREEN_STAINED_GLASS,
            Material.GREEN_STAINED_GLASS_PANE,

            Material.CYAN_STAINED_GLASS,
            Material.CYAN_STAINED_GLASS_PANE,

            Material.BLUE_STAINED_GLASS,
            Material.BLUE_STAINED_GLASS_PANE,

            Material.LIGHT_BLUE_STAINED_GLASS,
            Material.LIGHT_BLUE_STAINED_GLASS_PANE,

            Material.PURPLE_STAINED_GLASS,
            Material.PURPLE_STAINED_GLASS_PANE,

            Material.MAGENTA_STAINED_GLASS,
            Material.MAGENTA_STAINED_GLASS_PANE,

            Material.PINK_STAINED_GLASS,
            Material.PINK_STAINED_GLASS_PANE
    );
}
