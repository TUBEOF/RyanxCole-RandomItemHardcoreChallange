package de.tubeof.ryanxcole.rihc.tasks;

import de.tubeof.ryanxcole.rihc.data.Data;
import de.tubeof.ryanxcole.rihc.main.RIHC;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("unused")
public class ActionbarTimer {

    private final Data data = RIHC.getData();

    public ActionbarTimer() {
        start();
    }

    private BukkitTask bukkitTask;

    public void start() {
        bukkitTask = Bukkit.getScheduler().runTaskTimer(RIHC.getInstance(), () -> {
            if (data.isTimerRunning()) {
                long running = RIHC.getChallengeTimer().getTime();

                long years = running / 31540000L;
                long months = running / 2592000L % 12;
                long days = running / 86400L % 30;
                long hours = running / 3600L % 24;
                long minutes = running / 60L % 60;
                long seconds = running % 60;

                String runningText = (years == 0 ? "" : "§6" + years + " §eJahre, ") + (months == 0 ? "" : "§6" + months + " §eMonate, ") + (days == 0 ? "" : "§6" + days + " §eTage, ") + (hours == 0 ? "" : "§6" + hours + " §eStunden, ")
                        + (minutes == 0 ? "" : "§6" + minutes + " §eMinuten, ") + (seconds == 0 ? "" : "§6" + seconds + " §eSekunden, ") /* + (milliseconds == 0 ? "" : milliseconds + " Milliseconds, ") */;

                runningText = replaceLast(runningText, ", ", "");
                runningText = replaceLast(runningText, ",", " und");

                String finalRunningText = runningText;
                Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(finalRunningText)));
            } else {
                Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§o§cTimer pausiert")));
            }
        }, 0, 20);
    }

    public void stop() {
        if (bukkitTask != null) {
            bukkitTask.cancel();
            bukkitTask = null;
        }
    }

    private String replaceLast(final String text, final String regex, final String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}
