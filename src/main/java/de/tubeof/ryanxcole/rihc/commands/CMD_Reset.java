package de.tubeof.ryanxcole.rihc.commands;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("NullableProblems")
public class CMD_Reset implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            restartAndReset();
            return true;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission("rihc.reset")) {
            player.sendMessage("§cKeine Rechte!");
            return true;
        }

        restartAndReset();
        return true;
    }

    private void restartAndReset() {
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("§cServer wird zurückgesetzt ..."));
        RIHC.getInstance().getConfig().set("doReset", true);
        RIHC.getInstance().saveConfig();

        Bukkit.spigot().restart();
    }
}
