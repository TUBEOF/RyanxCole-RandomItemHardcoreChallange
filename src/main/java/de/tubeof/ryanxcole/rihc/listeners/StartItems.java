package de.tubeof.ryanxcole.rihc.listeners;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.tubetils.api.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("unused")
public class StartItems implements Listener {

    private final ItemBuilder itemBuilder = new ItemBuilder("RIHC-Startitems");
    private final HashMap<UUID, Integer> selectedItems = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            selectedItems.put(player.getUniqueId(), 0);

            Inventory inventory = Bukkit.createInventory(null, 27, "§2Wähle 3 Items zum Start");
            for (int slot = 0; slot < 27; slot++) {
                fillInventory(inventory, slot);
            }
            Bukkit.getScheduler().runTaskLater(RIHC.getInstance(), () -> player.openInventory(inventory), 5);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) return;
        Player player = (Player) event.getWhoClicked();
        if (!selectedItems.containsKey(player.getUniqueId())) return;
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null || itemStack.getType() == Material.AIR) return;
        if (event.getClickedInventory().getType() == InventoryType.PLAYER) return;

        int selected = selectedItems.get(player.getUniqueId());
        selected++;

        if (selected >= 3) {
            Bukkit.getScheduler().runTaskLater(RIHC.getInstance(), player::closeInventory, 1);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);

            selectedItems.remove(player.getUniqueId());
            return;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
        selectedItems.put(player.getUniqueId(), selected);
    }

    private Material getRandomMaterial() {
        Material[] materials = Material.values();
        int random = new Random().nextInt(materials.length);
        Material material = materials[random];

        return (material.isItem() || material.isBlock()) ? material : getRandomMaterial();
    }

    private boolean fillInventory(Inventory inventory, int slot) {
        Material material = getRandomMaterial();
        int amount = new Random().nextInt((material.getMaxStackSize() - 1) + 1) + 1;
        ItemStack itemStack = itemBuilder.simpleItemStack(material, amount);
        inventory.setItem(slot, itemStack);

        // Validate
        if (inventory.getItem(slot) == null) {
            return fillInventory(inventory, slot);
        }
        Bukkit.broadcastMessage("#" + slot + " A" + amount + " M" + material);
        return true;
    }
}
