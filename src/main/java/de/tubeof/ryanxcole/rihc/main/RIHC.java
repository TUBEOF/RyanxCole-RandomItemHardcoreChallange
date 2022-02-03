package de.tubeof.ryanxcole.rihc.main;

import de.tubeof.ryanxcole.rihc.commands.CMD_Reset;
import de.tubeof.ryanxcole.rihc.commands.CMD_Settings;
import de.tubeof.ryanxcole.rihc.commands.CMD_Timer;
import de.tubeof.ryanxcole.rihc.data.Data;
import de.tubeof.ryanxcole.rihc.listeners.Death;
import de.tubeof.ryanxcole.rihc.listeners.EnderdragonKill;
import de.tubeof.ryanxcole.rihc.listeners.ItemPickUp;
import de.tubeof.ryanxcole.rihc.listeners.RandomItemsInv;
import de.tubeof.ryanxcole.rihc.listeners.SettingsMenuListener;
import de.tubeof.ryanxcole.rihc.listeners.StartItems;
import de.tubeof.ryanxcole.rihc.tasks.ActionbarTimer;
import de.tubeof.ryanxcole.rihc.tasks.ChallengeTimer;
import de.tubeof.ryanxcole.rihc.tasks.HintsTimer;
import de.tubeof.ryanxcole.rihc.tasks.RandomItemTimer;
import de.tubeof.ryanxcole.rihc.utils.basics.ChallengeReset;
import de.tubeof.ryanxcole.rihc.utils.basics.RIHCLogger;
import de.tubeof.ryanxcole.rihc.utils.basics.SettingsMenu;
import de.tubeof.tubetils.api.cache.CacheContainer;
import de.tubeof.tubetilsmanager.TubeTilsManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ConstantConditions")
public class RIHC extends JavaPlugin {

    private static RIHC instance;
    private static TubeTilsManager tubeTilsManager;
    private static CacheContainer cacheContainer;
    private static RIHCLogger rihcLogger;
    private static Data data;
    private static ActionbarTimer actionbarTimer;
    private static ChallengeTimer challengeTimer;
    private static RandomItemTimer randomItemTimer;
    private static HintsTimer hintsTimer;
    private static SettingsMenu settingsMenu;

    @Override
    public void onLoad() {
        instance = this;

        saveConfig();
        if (getConfig().getBoolean("doReset", false)) new ChallengeReset();
    }

    @Override
    public void onEnable() {
        initialisation();
        if (!tubeTilsManager.wasSuccessful()) return;

        long startTimestamp = System.currentTimeMillis();
        rihcLogger.info("§aThe Plugin will be activated ...");

        registerCacheContainer();
        registerCommands();
        registerListeners();

        setupMetrics();

        long startTime = System.currentTimeMillis() - startTimestamp;
        rihcLogger.info("§aThe plugin was successfully activated in §e" + startTime + "ms§a!");
    }

    @Override
    public void onDisable() {
        if (!tubeTilsManager.wasSuccessful()) return;

        rihcLogger.info("§aThe Plugin will be deactivated ...");

        challengeTimer.stop();
        
        rihcLogger.info("§aThe plugin was successfully deactivated!");
    }

    private void initialisation() {
        tubeTilsManager = new TubeTilsManager("[RIHC-Logger] ", getInstance(), 75, true);

        rihcLogger = new RIHCLogger();
        data = new Data();
        challengeTimer = new ChallengeTimer(getConfig().getLong("timerTime", 0));
        actionbarTimer = new ActionbarTimer();
        randomItemTimer = new RandomItemTimer();
        hintsTimer = new HintsTimer();
        settingsMenu = new SettingsMenu();
    }

    private void registerCacheContainer() {
        cacheContainer = new CacheContainer("RIHC");
        cacheContainer.registerCacheType(String.class);
        cacheContainer.registerCacheType(Boolean.class);
        cacheContainer.registerCacheType(Integer.class);
    }

    private void registerCommands() {
        rihcLogger.info("§aCommands will be registered ...");

        getCommand("reset").setExecutor(new CMD_Reset());
        getCommand("timer").setExecutor(new CMD_Timer());
        getCommand("settings").setExecutor(new CMD_Settings());

        rihcLogger.info("§aCommands have been successfully registered!");
    }

    private void registerListeners() {
        rihcLogger.info("§aListeners will be registered ...");

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new RandomItemsInv(), this);
        pluginManager.registerEvents(new StartItems(), this);
        pluginManager.registerEvents(new ItemPickUp(), this);
        pluginManager.registerEvents(new Death(), this);
        pluginManager.registerEvents(new EnderdragonKill(), this);
        pluginManager.registerEvents(new SettingsMenuListener(), this);

        rihcLogger.info("§aListeners have been successfully registered!");
    }

    @SuppressWarnings("unused")
    private void setupMetrics() {
        rihcLogger.info("§aEnabling metrics ...");

        Metrics metrics = new Metrics(getInstance(), 14086);

        rihcLogger.info("§aMetrics was successfully enabled!");
    }

    public static RIHC getInstance() {
        return instance;
    }

    @SuppressWarnings("unused")
    public static CacheContainer getCacheContainer() {
        return cacheContainer;
    }

    @SuppressWarnings("unused")
    public static RIHCLogger getRihcLogger() {
        return rihcLogger;
    }

    @SuppressWarnings("unused")
    public static Data getData() {
        return data;
    }

    @SuppressWarnings("unused")
    public static ChallengeTimer getChallengeTimer() {
        return challengeTimer;
    }

    @SuppressWarnings("unused")
    public static ActionbarTimer getActionbarTimer() {
        return actionbarTimer;
    }

    @SuppressWarnings("unused")
    public static RandomItemTimer getRandomItemTimer() {
        return randomItemTimer;
    }

    @SuppressWarnings("unused")
    public static HintsTimer getHintsTimer() {
        return hintsTimer;
    }

    @SuppressWarnings("unused")
    public static SettingsMenu getSettingsMenu() {
        return settingsMenu;
    }
}
