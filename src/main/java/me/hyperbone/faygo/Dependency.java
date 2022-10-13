package me.hyperbone.faygo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.util.logging.Level;

public class Dependency {

    private final Repository repository;
    private final String group;
    private final String name;
    private final String version;
    private final Boolean customRepository;
    private final String repositoryURL;

    private final DependencyLoader loader = new DependencyLoader((URLClassLoader) Faygo.getPlugin().getClass().getClassLoader());

    public Dependency(Repository repository, String group, String name, String version) {
        this.repositoryURL = null;
        this.customRepository = false;
        this.repository = repository;
        this.group = group;
        this.name = name;
        this.version = version;
    }

    public Dependency(String repositoryURL, String group, String name, String version) {
        this.repositoryURL = repositoryURL;
        this.customRepository = true;
        this.repository = Repository.CUSTOM;
        this.group = group;
        this.name = name;
        this.version = version;
    }

    private String getJarName() {
        return name + "-v" + version + ".jar";
    }

    private String getDownloadURL() {
        final String repo = String.format(repository.getRepositoryURL(), group.replace('.', '/') + "/" + name + "/" + version + "/" + name + "-" + version);
        if (customRepository) {
            if (repositoryURL == null) {
                Faygo.sendConsoleMessage(ChatColor.RED + "リポジトリのURLが無効です！プラグインを無効にします...");
                Bukkit.getPluginManager().disablePlugin(Faygo.getPlugin());
            }
            return repo.replace("repo", repositoryURL);
        }
        return repo;
    }

    public void downloadAndLoad() {
        final File file = download();
        if (file.length() != 0) {
            load(file);
        } else {
            file.delete();
        }
    }

    private void load(File file) {
        loader.loadDependency(file);
    }

    private File download() {
        File dir = new File(Faygo.getPath());
        if (!dir.exists()) dir.mkdirs();

        File file = new File(Faygo.getPath() + File.separator + getJarName());
        if (file.exists()) return file;

        Faygo.sendConsoleMessage(ChatColor.GOLD + "ダウンロード開始: " + ChatColor.YELLOW + name + " v" + version);

        try (FileOutputStream outputStream = new FileOutputStream(Faygo.getPath() + File.separator + getJarName())) {
            outputStream.getChannel().transferFrom(Channels.newChannel(new URL(getDownloadURL()).openStream()), 0, Long.MAX_VALUE);
            Faygo.sendConsoleMessage(ChatColor.GREEN + "ダウンロードが完了しました！");
        } catch (IOException ex) {
            if (ex instanceof FileNotFoundException) {
                Faygo.sendConsoleMessage(Level.WARNING, ChatColor.RED + "ダウンロードに失敗しました！指定されたリポジトリは既に削除されている可能性があります！");
                Faygo.sendConsoleMessage(Level.WARNING, ChatColor.RED + "リポジトリの設定が正しいか確認してください。");
            } else {
                ex.printStackTrace();
            }
        }
        return file;
    }
}

