package de.tubeof.ryanxcole.rihc.listeners;

import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

@SuppressWarnings("unused")
public class ItemPickUp implements Listener {

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onPickUp(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (player.getGameMode() != GameMode.SURVIVAL) return;

        if (player.getHealthScale() == 2) player.setHealth(0);
        else {
            player.damage(2);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() - 2);
            player.saveData();
        }
    }
}
