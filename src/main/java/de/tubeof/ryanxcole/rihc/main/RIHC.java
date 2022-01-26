package de.tubeof.ryanxcole.rihc.main;

import de.tubeof.ryanxcole.rihc.utils.basics.RIHCLogger;
import de.tubeof.tubetils.api.cache.CacheContainer;
import de.tubeof.tubetilsmanager.TubeTilsManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class RIHC extends JavaPlugin {

    private static RIHC instance;
    private static TubeTilsManager tubeTilsManager;
    private static CacheContainer cacheContainer;
    private static RIHCLogger rihcLogger;

    @Override
    public void onEnable() {
        long startTimestamp = System.currentTimeMillis();

        initialisation();
        if (!tubeTilsManager.wasSuccessful()) return;

        rihcLogger.info("§aThe Plugin will be activated ...");

        registerCommands();
        registerListeners();

        long startTime = System.currentTimeMillis() - startTimestamp;
        rihcLogger.info("§aThe plugin was successfully activated in §e" + startTime + "ms§a!");
    }

    @Override
    public void onDisable() {
        if (!tubeTilsManager.wasSuccessful()) return;

        rihcLogger.info("§aThe Plugin will be deactivated ...");
        
        rihcLogger.info("§aThe plugin was successfully deactivated!");
    }

    private void initialisation() {
        instance = this;

        tubeTilsManager = new TubeTilsManager("[RIHC-Logger] ", getInstance(), 71, true);
        cacheContainer = new CacheContainer("RIHC");
        cacheContainer.registerCacheType(String.class);
        cacheContainer.registerCacheType(Boolean.class);
        cacheContainer.registerCacheType(Integer.class);

        rihcLogger = new RIHCLogger();
    }

    private void registerCommands() {
        rihcLogger.info("§aCommands will be registered ...");

        rihcLogger.info("§aCommands have been successfully registered!");
    }

    private void registerListeners() {
        rihcLogger.info("§aListeners will be registered ...");

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
}
