package de.tubeof.ryanxcole.rihc.listeners;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.tasks.ChallengeTimer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    private final ChallengeTimer challengeTimer = RIHC.getChallengeTimer();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        challengeTimer.stop();

        Player death = event.getEntity();
        death.setHealth(20);
        death.setHealthScale(20);

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setHealth(20);
            player.setHealthScale(20);
            player.setGameMode(GameMode.SPECTATOR);

            player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 2, 0.1F);
            player.sendMessage("§c§lChallenge fehlgeschlagen! §8[§e/reset§8]");
            player.sendMessage("§6§l" + death.getName() + " §cist gestorben!");
        });
    }
}
