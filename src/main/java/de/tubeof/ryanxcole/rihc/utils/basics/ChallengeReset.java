package de.tubeof.ryanxcole.rihc.utils.basics;

import de.tubeof.ryanxcole.rihc.main.RIHC;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;

public class ChallengeReset {

    public ChallengeReset() {
        worldReset();
        resetTimer();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void worldReset() {
        File world = new File(Bukkit.getWorldContainer(), "world");
        File nether = new File(Bukkit.getWorldContainer(), "world_nether");
        File end = new File(Bukkit.getWorldContainer(), "world_the_end");

        try {
            FileUtils.deleteDirectory(world);
            FileUtils.deleteDirectory(nether);
            FileUtils.deleteDirectory(end);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Recreate default world folders to avoid bugs on older versions
        world.mkdirs();
        new File(world, "data").mkdirs();
        new File(world, "datapacks").mkdirs();
        new File(world, "playerdata").mkdirs();
        new File(world, "poi").mkdirs();
        new File(world, "region").mkdirs();

        nether.mkdirs();
        new File(nether, "data").mkdirs();
        new File(nether, "datapacks").mkdirs();
        new File(nether, "playerdata").mkdirs();
        new File(nether, "poi").mkdirs();
        new File(nether, "region").mkdirs();

        end.mkdirs();
        new File(end, "data").mkdirs();
        new File(end, "datapacks").mkdirs();
        new File(end, "playerdata").mkdirs();
        new File(end, "poi").mkdirs();
        new File(end, "region").mkdirs();

        RIHC.getInstance().getConfig().set("doReset", false);
        RIHC.getInstance().saveConfig();
    }

    private void resetTimer() {
        RIHC.getInstance().getConfig().set("timerTime", 0);
        RIHC.getInstance().saveConfig();
    }
}
