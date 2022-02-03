package de.tubeof.ryanxcole.rihc.tasks;

import de.tubeof.ryanxcole.rihc.data.Data;
import de.tubeof.ryanxcole.rihc.main.RIHC;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unused")
public class ChallengeTimer {

    private final Data data = RIHC.getData();

    public ChallengeTimer(long time) {
        this.TIME = time;
    }

    private long TIME;
    private BukkitTask bukkitTask;

    public void start() {
        Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2));

        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.valueOf(RIHC.getInstance().getConfig().getString("difficulty", "HARD")));
            world.setGameRule(GameRule.NATURAL_REGENERATION, RIHC.getInstance().getConfig().getBoolean("regeneration", false));
        }

        data.setTimerRunning(true);
        bukkitTask = Bukkit.getScheduler().runTaskTimer(RIHC.getInstance(), () -> {
            TIME++;
        }, 0, 20);
    }

    public void stop() {
        if (bukkitTask != null) {
            Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5F, 2));

            bukkitTask.cancel();
            data.setTimerRunning(false);
            bukkitTask = null;
        }

        RIHC.getInstance().getConfig().set("timerTime", TIME);
        RIHC.getInstance().saveConfig();
    }

    public void setTime(long time) {
        this.TIME = time;
    }

    public long getTime() {
        return TIME;
    }
}
