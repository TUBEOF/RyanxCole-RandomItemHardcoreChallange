package de.tubeof.ryanxcole.rihc.utils.basics;

import de.tubeof.ryanxcole.rihc.main.RIHC;

@SuppressWarnings("unused")
public class RIHCLogger {

    public RIHCLogger() {}

    public void info(String message) {
        RIHC.getInstance().getLogger().info(message);
    }

    public void warn(String message) {
        RIHC.getInstance().getLogger().warning(message);
    }

    public void debug(Class<?> paramClass, String message) {
        RIHC.getInstance().getLogger().info("[DEBUG] [" + paramClass.getName() + "]§r " + message);
    }
}
