package de.tubeof.ryanxcole.rihc.utils.randomizer;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.utils.basics.RIHCLogger;
import de.tubeof.tubetils.api.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RandomInv {

    private final RIHCLogger rihcLogger = RIHC.getRihcLogger();
    private final ItemBuilder itemBuilder = new ItemBuilder("RIHC-RandomInv");

    private final String name;
    private final int size;

    public RandomInv(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public Inventory get() {
        Inventory inventory = Bukkit.createInventory(null, size, name);
        for (int slot = 0; slot < size; slot++) {
            fillInventory(inventory, slot);
        }
        return inventory;
    }

    private Material getRandomMaterial() {
        Material[] materials = Material.values();
        int random = new Random().nextInt(materials.length);
        Material material = materials[random];

        return (material.isItem() || material.isBlock()) ? material : getRandomMaterial();
    }

    private boolean fillInventory(Inventory inventory, int slot) {
        Material material = getRandomMaterial();
        int amount;
        try {
            amount = (material.getMaxStackSize() == 1) ? 1 : new Random().nextInt(1, material.getMaxStackSize());
        } catch (Exception exception) {
            return fillInventory(inventory, slot);
        }
        ItemStack itemStack = itemBuilder.simpleItemStack(material, amount);
        inventory.setItem(slot, itemStack);

        // Validate
        if (inventory.getItem(slot) == null) {
            return fillInventory(inventory, slot);
        }
        rihcLogger.debug(getClass(), "S:" + slot + ";A:" + amount + ";M:" + material);
        return true;
    }
}
