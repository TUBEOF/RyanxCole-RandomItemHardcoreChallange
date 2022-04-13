package de.tubeof.ryanxcole.rihc.listeners;

import de.tubeof.ryanxcole.rihc.data.Data;
import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.tasks.ChallengeTimer;
import de.tubeof.ryanxcole.rihc.tasks.RandomItemTimer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@SuppressWarnings("unused")
public class Death implements Listener {

    private final Data data = RIHC.getData();
    private final ChallengeTimer challengeTimer = RIHC.getChallengeTimer();
    private final RandomItemTimer randomItemTimer = RIHC.getRandomItemTimer();

    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (challengeTimer.getTime() == 0) return;

        data.setPlayerDied(true);
        challengeTimer.stop();
        randomItemTimer.stop();

        Player death = event.getEntity();
        death.setHealth(20);
        death.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        death.saveData();
        death.spigot().respawn();

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.setHealth(20);
            player.setHealthScale(20);
            player.setGameMode(GameMode.SPECTATOR);

            player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 2, 0.1F);
            player.sendMessage("§7★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            player.sendMessage("    §c§lChallenge fehlgeschlagen! §8[§e/reset§8]");
            player.sendMessage("    §6§l" + death.getName() + " §cist gestorben!");
            player.sendMessage("§7★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            player.sendTitle("§c✖", "§c" + death.getName() + " ist gestorben", 0, 60, 5);
        });
    }
}
