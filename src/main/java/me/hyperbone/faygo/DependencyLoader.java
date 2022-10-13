package me.hyperbone.faygo;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

class DependencyLoader {

    private static URLClassLoader urlClassLoader;
    private final List<File> loadedFiles = new ArrayList<>();

    public DependencyLoader(final URLClassLoader urlClassLoader) {
        this.urlClassLoader = urlClassLoader;
    }

    public void loadDependency(@NotNull File file) {
        if (loadedFiles.contains(file)) return;

        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(urlClassLoader, file.toURI().toURL());
            loadedFiles.add(file);
            Faygo.getPlugin().getLogger().info(ChatColor.AQUA + file.getName() + "の読み込みが完了しました！");
            Faygo.sendConsoleMessage(ChatColor.AQUA + file.getName() + "の読み込みが完了しました！");
        } catch (ClassCastException | NoSuchMethodException | MalformedURLException | IllegalAccessException |
                 InvocationTargetException ex) {
        }
    }
}
