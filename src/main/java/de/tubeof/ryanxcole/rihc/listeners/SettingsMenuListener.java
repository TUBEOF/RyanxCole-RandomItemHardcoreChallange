package de.tubeof.ryanxcole.rihc.listeners;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.utils.basics.SettingsMenu;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("ConstantConditions")
public class SettingsMenuListener implements Listener {

    private final SettingsMenu settingsMenu = RIHC.getSettingsMenu();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null) return;
        Player player = (Player) event.getWhoClicked();
        if (!event.getView().getTitle().equalsIgnoreCase("§2§lEinstellungen")) return;
        event.setCancelled(true);
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null || itemStack.getType() == Material.AIR) return;
        if (!itemStack.hasItemMeta()) return;

        if (itemStack.getItemMeta().getDisplayName().equals("§2Einstellungen übernehmen")) {
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
        }

        if (itemStack.getItemMeta().getDisplayName().equals("§aRegeneration aktiv")) {
            RIHC.getInstance().getConfig().set("regeneration", false);
            RIHC.getInstance().saveConfig();

            settingsMenu.updateData();
            settingsMenu.fillInventory();
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        } else if (itemStack.getItemMeta().getDisplayName().equals("§cRegeneration inaktiv")) {
            RIHC.getInstance().getConfig().set("regeneration", true);
            RIHC.getInstance().saveConfig();

            settingsMenu.updateData();
            settingsMenu.fillInventory();
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }

        if (itemStack.getItemMeta().getDisplayName().equals("§cModus: HARD")) {
            RIHC.getInstance().getConfig().set("difficulty", "HARD");
            RIHC.getInstance().saveConfig();

            settingsMenu.updateData();
            settingsMenu.fillInventory();
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        } else if (itemStack.getItemMeta().getDisplayName().equals("§aModus: NORMAL")) {
            RIHC.getInstance().getConfig().set("difficulty", "NORMAL");
            RIHC.getInstance().saveConfig();

            settingsMenu.updateData();
            settingsMenu.fillInventory();
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }

        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.valueOf(RIHC.getInstance().getConfig().getString("difficulty", "HARD")));
            world.setGameRule(GameRule.NATURAL_REGENERATION, RIHC.getInstance().getConfig().getBoolean("regeneration", false));
        }
    }
}
