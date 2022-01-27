package de.tubeof.ryanxcole.rihc.listeners;

import net.minecraft.network.PacketListener;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

@SuppressWarnings("unused")
public class ItemPickUp implements Listener {

    @EventHandler
    public void onPickUp(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof PacketListener)) return;
        Player player = (Player) event.getEntity();
        if (player.getGameMode() != GameMode.SURVIVAL) return;

        if (player.isSneaking()) {
            event.setCancelled(true);
            return;
        }

        player.setHealthScale((player.getHealthScale() - 1));
    }
}
