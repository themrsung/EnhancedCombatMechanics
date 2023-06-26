package jbs.enhancedcombatmechanics;

import jbs.enhancedcombatmechanics.listerners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnhancedCombatMechanics extends JavaPlugin {

    @Override
    public void onEnable() {
        new PlayerJoinEventListener(this);

        new MeleeCombatListener(this);
        new RangedCombatListener(this);
        new KineticDamageListener(this);

        new FirearmsUsageListener(this);
        new BodyArmorHandler(this);

        new NuclearWeaponsHandler(this);
    }

}
