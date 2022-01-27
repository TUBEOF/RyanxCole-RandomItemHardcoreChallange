package de.tubeof.ryanxcole.rihc.listeners;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.tasks.ChallengeTimer;
import de.tubeof.ryanxcole.rihc.tasks.RandomItemTimer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

@SuppressWarnings("unused")
public class EnderdragonKill implements Listener {

    private final ChallengeTimer challengeTimer = RIHC.getChallengeTimer();
    private final RandomItemTimer randomItemTimer = RIHC.getRandomItemTimer();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof EnderDragon)) return;
        Entity killer = ((EnderDragon) entity).getKiller();

        challengeTimer.stop();
        randomItemTimer.stop();

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 2, 1F);
            player.sendMessage("§7★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            player.sendMessage("    §2§lChallenge erfolgreich beendet!");
            player.sendMessage("    §e§l" + killer.getName() + " §ahat den §5§lEnderdrachen §agetötet!");
            player.sendMessage("§7★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        });
    }
}
