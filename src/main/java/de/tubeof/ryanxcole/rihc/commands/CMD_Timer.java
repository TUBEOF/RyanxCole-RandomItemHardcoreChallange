package de.tubeof.ryanxcole.rihc.commands;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.tasks.ChallengeTimer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Timer implements CommandExecutor {

    private final ChallengeTimer challengeTimer = RIHC.getChallengeTimer();

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
            challengeTimer.start();
        } else if (arg.equalsIgnoreCase("pause")) {
            challengeTimer.stop();
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
