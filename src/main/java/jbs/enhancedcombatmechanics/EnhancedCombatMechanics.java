package jbs.enhancedcombatmechanics;

import jbs.enhancedcombatmechanics.listerners.MeleeCombatListener;
import jbs.enhancedcombatmechanics.listerners.PlayerJoinEventListener;
import jbs.enhancedcombatmechanics.listerners.RangedCombatListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnhancedCombatMechanics extends JavaPlugin {

    @Override
    public void onEnable() {
        new PlayerJoinEventListener(this);

        new MeleeCombatListener(this);
        new RangedCombatListener(this);
    }

}
