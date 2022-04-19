package de.tubeof.ryanxcole.rihc.listeners;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

@SuppressWarnings("unused")
public class RandomItemsInv implements Listener {

    private final HashMap<UUID, Integer> selectedItems = new HashMap<>();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) return;
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null || itemStack.getType() == Material.AIR) return;
        if (!inventory.getType().equals(InventoryType.CHEST)) return;
        if (!event.getView().getTitle().equalsIgnoreCase("§2Wähle bis zu 2 Items")) return;

        int selected = selectedItems.getOrDefault(player.getUniqueId(), 0);
        selected++;

        if (selected >= 2) {
            Bukkit.getScheduler().runTaskLater(RIHC.getInstance(), player::closeInventory, 1);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);

            selectedItems.remove(player.getUniqueId());
            return;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
        selectedItems.put(player.getUniqueId(), selected);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        selectedItems.remove(event.getPlayer().getUniqueId());
    }
}
