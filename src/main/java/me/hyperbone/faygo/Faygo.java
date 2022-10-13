package me.hyperbone.faygo;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.logging.Level;

public class Faygo {

    @Getter
    private final DependencyBuilder dependencyBuilder = new DependencyBuilder();
    @Getter
    private static Plugin plugin;
    @Getter
    private static String path = null;

    public Faygo(Plugin plugin) {
        Faygo.plugin = plugin;
        path = plugin.getDataFolder().getParent() + File.separator + plugin.getDescription().getName() + File.separator + "libraries";
    }

    public Faygo(Plugin plugin, String path) {
        Faygo.plugin = plugin;
        Faygo.path = plugin.getDataFolder().getParent() + path;
    }

    public static void sendConsoleMessage(String message) {
        plugin.getLogger().info(message);
    }

    public static void sendConsoleMessage(Level level, String message) {
        plugin.getLogger().log(level, message);
    }
}
