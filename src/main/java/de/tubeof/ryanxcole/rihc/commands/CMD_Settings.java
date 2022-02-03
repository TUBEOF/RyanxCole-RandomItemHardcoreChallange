package de.tubeof.ryanxcole.rihc.commands;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import de.tubeof.ryanxcole.rihc.utils.basics.SettingsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("NullableProblems")
public class CMD_Settings implements CommandExecutor {

    private final SettingsMenu settingsMenu = RIHC.getSettingsMenu();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("§cDu musst ein Spieler sein!");
            return true;
        }
        Player player = (Player) commandSender;
        if (!player.hasPermission("rihc.settings")) {
            player.sendMessage("§cKeine Rechte!");
            return true;
        }

        player.openInventory(settingsMenu.getInventory());
        return true;
    }
}
