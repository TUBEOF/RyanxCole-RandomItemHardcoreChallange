package de.tubeof.ryanxcole.rihc.commands;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.tasks.ChallengeTimer;
import de.tubeof.ryanxcole.rihc.tasks.RandomItemTimer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Timer implements CommandExecutor {

    private final ChallengeTimer challengeTimer = RIHC.getChallengeTimer();
    private final RandomItemTimer randomItemTimer = RIHC.getRandomItemTimer();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cKann nur als Spieler ausgeführt werden!");
            return true;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission("rihc.timer")) {
            player.sendMessage("§cKeine Rechte!");
            return true;
        }

        if (args.length == 0) {
            sendUsage(player);
            return true;
        }

        String arg = args[0];
        if (arg.equalsIgnoreCase("resume")) {
            player.sendMessage("§7★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            player.sendMessage("§6§lERKLÄRUNG ...");
            player.sendMessage("§2§lZiel ...");
            player.sendMessage("§7- §aVersuche Minecaft durchzuspielen (Enderdrachen töten)");
            player.sendMessage("§2§lEinschränkungen ...");
            player.sendMessage("§7- §aKeine Items einsammeln, andernfalls verlierst du §cdauerhaft §aein Herz + Schaden");
            player.sendMessage("§7- §aAlle §25 Minuten §akannst du 2 Items aus 36 Items auswählen");
            player.sendMessage("§7- §aWenn §2ein Spieler §astirbt, ist die Challenge vorbei");
            player.sendMessage("§7★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");

            challengeTimer.start();
            randomItemTimer.start();
        } else if (arg.equalsIgnoreCase("pause")) {
            challengeTimer.stop();
            randomItemTimer.stop();
        } else if (arg.equalsIgnoreCase("reset")) {
            challengeTimer.setTime(0);
        } else {
            sendUsage(player);
        }

        return true;
    }

    private void sendUsage(Player player) {
        player.sendMessage("§c- /timer resume");
        player.sendMessage("§c- /timer pause");
        player.sendMessage("§c- /timer reset");
    }
}
