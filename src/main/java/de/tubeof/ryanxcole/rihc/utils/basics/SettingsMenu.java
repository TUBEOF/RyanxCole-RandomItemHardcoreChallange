package de.tubeof.ryanxcole.rihc.utils.basics;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.tubetils.api.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class SettingsMenu {

    private final ItemBuilder itemBuilder = new ItemBuilder("RIHC-Settings");

    private final Inventory inventory = Bukkit.createInventory(null, 27, "§2§lEinstellungen");
    private boolean regeneration;
    private Difficulty difficulty;

    public SettingsMenu() {
        updateData();
        fillInventory();
    }

    public void updateData() {
        this.regeneration = RIHC.getInstance().getConfig().getBoolean("regeneration", false);
        this.difficulty = Difficulty.valueOf(RIHC.getInstance().getConfig().getString("difficulty", "HARD"));
    }

    public void fillInventory() {
        // Fill inventory
        for (int slot = 0; slot < 27; slot++) {
            inventory.setItem(slot, itemBuilder.simpleItemStack(Material.BLACK_STAINED_GLASS, 1, "§f"));
        }

        if (regeneration) inventory.setItem(10, itemBuilder.simpleItemStack(Material.GREEN_DYE, 1, "§aRegeneration aktiv"));
        else inventory.setItem(10, itemBuilder.simpleItemStack(Material.RED_DYE, 1, "§cRegeneration inaktiv"));

        if (difficulty == Difficulty.HARD) inventory.setItem(12, itemBuilder.simpleItemStack(Material.GREEN_DYE, 1, "§cModus: HARD"));
        else inventory.setItem(12, itemBuilder.simpleItemStack(Material.RED_DYE, 1, "§cModus: HARD"));
        if (difficulty == Difficulty.NORMAL) inventory.setItem(13, itemBuilder.simpleItemStack(Material.GREEN_DYE, 1, "§aModus: NORMAL"));
        else inventory.setItem(13, itemBuilder.simpleItemStack(Material.RED_DYE, 1, "§aModus: NORMAL"));

        // Save settings button
        inventory.setItem(6, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));
        inventory.setItem(7, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));
        inventory.setItem(8, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));
        inventory.setItem(15, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));
        inventory.setItem(16, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));
        inventory.setItem(17, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));
        inventory.setItem(24, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));
        inventory.setItem(25, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));
        inventory.setItem(26, itemBuilder.simpleItemStack(Material.GREEN_STAINED_GLASS_PANE, 1, "§2Einstellungen übernehmen"));

    }

    public Inventory getInventory() {
        return inventory;
    }
}
