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
            for (int i = 0; i < 27; i++) {
                Material material = getRandomItem();
                inventory.setItem(i, itemBuilder.simpleItemStack(material, new Random().nextInt(material.getMaxStackSize())));

                Bukkit.broadcastMessage("" + material + " #" + i);
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
            Bukkit.getScheduler().runTaskLater(RIHC.getInstance(), () -> player.closeInventory(), 1);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);

            selectedItems.remove(player.getUniqueId());
            return;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 1);
        selectedItems.put(player.getUniqueId(), selected);
    }

    private Material getRandomItem() {
        Material[] materials = Material.values();
        int random = new Random().nextInt(materials.length);
        Material material = materials[random];
        int amount = new Random().nextInt((material.getMaxStackSize() - 1) + 1) + 1;

        return (material.isItem() || material.isBlock()) ? material : getRandomItem();
    }
}
