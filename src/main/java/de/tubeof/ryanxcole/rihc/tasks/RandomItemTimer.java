package de.tubeof.ryanxcole.rihc.tasks;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.utils.randomizer.RandomInv;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unused")
public class RandomItemTimer {

    public RandomItemTimer() {}

    private BukkitTask bukkitTask;
    private int cycle = 0;

    public void start() {
        bukkitTask = Bukkit.getScheduler().runTaskTimer(RIHC.getInstance(), () -> {
            cycle++;

            if (cycle == 240) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
                    player.sendMessage("§aIn §e60 Sekunden §akönnen neue §eRandom-Items §agewählt werden.");
                });
            }
            if (cycle == 270) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
                    player.sendMessage("§aIn §e30 Sekunden §akönnen neue §eRandom-Items §agewählt werden.");
                });
            }

            if (cycle == 295) Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.5F));
            if (cycle == 296) Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.6F));
            if (cycle == 297) Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.7F));
            if (cycle == 298) Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.8F));
            if (cycle == 299) Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.9F));

            if (cycle == 300) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 2, 0.1F);

                    Inventory inventory = new RandomInv("§aWähle bis zu §22 Items", 36).get();
                    player.openInventory(inventory);
                });

                new BukkitRunnable() {
                    int cycle = 0;

                    @Override
                    public void run() {
                        cycle++;
                        if (!(cycle >= 25)) return;

                        Bukkit.getOnlinePlayers().forEach(player -> {
                            if (!player.getOpenInventory().getTitle().equalsIgnoreCase("§aWähle bis zu §22 Items")) return;

                            if (cycle == 25) player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.5F);
                            else if (cycle == 26) player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.6F);
                            else if (cycle == 27) player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.7F);
                            else if (cycle == 28) player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.8F);
                            else if (cycle == 29) player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 0.9F);
                            else if (cycle == 30) {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 1);
                                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                                player.closeInventory();

                                cancel();
                            }
                        });
                    }
                }.runTaskTimer(RIHC.getInstance(), 20, 20);
            }

            if (cycle == 340) {
                cycle = 0;
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
                    player.sendMessage("§aTimer läuft wieder! In §e5 Minuten §agibt es die nächsten Items.");
                });
            }
        }, 0, 20);
    }

    public void stop() {
        if (bukkitTask != null) {
            bukkitTask.cancel();
            bukkitTask = null;
            cycle = 0;
        }
    }
}
