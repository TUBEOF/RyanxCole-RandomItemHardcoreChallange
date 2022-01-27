package de.tubeof.ryanxcole.rihc.tasks;

import de.tubeof.ryanxcole.rihc.data.Data;
import de.tubeof.ryanxcole.rihc.main.RIHC;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unused")
public class HintsTimer {

    private final Data data = RIHC.getData();

    public HintsTimer() {
        start();
    }

    private BukkitTask bukkitTask;

    public void start() {
        bukkitTask = Bukkit.getScheduler().runTaskTimer(RIHC.getInstance(), () -> {
            if (!data.isTimerRunning()) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendTitle("§f", "§c/timer resume", 5, 20, 5);
                });
            }
            if (data.isPlayerDied()) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendTitle("§f", "§c/reset", 5, 20, 5);
                });
            }
        }, 0, 20 * 10);
    }

    public void stop() {
        if (bukkitTask != null) {
            bukkitTask.cancel();
            bukkitTask = null;
        }
    }
}
