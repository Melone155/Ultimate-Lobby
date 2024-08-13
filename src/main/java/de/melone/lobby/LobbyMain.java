package de.melone.lobby;

import com.mojang.brigadier.Command;
import de.melone.lobby.cmd.CMD_build;
import de.melone.lobby.cmd.CMD_fly;
import de.melone.lobby.cmd.CMD_gm;
import de.melone.lobby.cmd.CMD_setspawn;
import de.melone.lobby.listener.Buildlistener;
import de.melone.lobby.listener.CancelledEvent;
import de.melone.lobby.listener.JoinQuit;
import de.melone.lobby.listener.Updates;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public final class LobbyMain extends JavaPlugin {

    File folder = new File("plugins/Lobby");


    public static File messagefile = new File("plugins//Lobby//Messages.yml");
    public static YamlConfiguration messageyml = YamlConfiguration.loadConfiguration(messagefile);

    public static File configfile = new File("plugins//Lobby//Config.yml");
    public static YamlConfiguration configyml = YamlConfiguration.loadConfiguration(configfile);

    public static String prefix = messageyml.getString("Message.prefix");
    public static String noperms = messageyml.getString("Message.noperms");

    @Override
    public void onEnable() {

        registercommands();
        registerlistener();
        registerconfig();

        Updates.LoadeBook();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registercommands() {
        getCommand("gm").setExecutor(new CMD_gm());
        getCommand("build").setExecutor(new CMD_build());
        getCommand("fly").setExecutor(new CMD_fly());
        getCommand("setspawn").setExecutor(new CMD_setspawn());
    }

    private void registerlistener() {
        final PluginManager pluginManager = super.getServer().getPluginManager();

        pluginManager.registerEvents(new Buildlistener(), this);
        pluginManager.registerEvents(new JoinQuit(), this);
        pluginManager.registerEvents(new CancelledEvent(), this);
    }

    private void registerconfig() {

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!messagefile.exists()){
            try {
                messagefile.createNewFile();
                MessageConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (!configfile.exists()){
            try {
                configfile.createNewFile();
                Config();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void NoSQLConfig(){

    }

    private void MessageConfig(){
        String info = "# All die Messages Support MiniMessages https://docs.advntr.dev/minimessage/format.html";

        // Schreibe den Infotext in die Datei
        try {
            Files.write(messagefile.toPath(), info.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Setze die Standardwerte für die Nachrichten
        messageyml.set("Message.prefix", "[Prefix]");
        messageyml.set("Message.noperms", "You have no permissions for this Command");
        messageyml.set("Message.setspawn", "You Have set The Spawn");
        messageyml.set("Message.error", "Oh no, it seems something has gone wrong");

        messageyml.set("Message.help", "Use /gm <0,1,2,3> [Player]");

        messageyml.set("Message.gamemode.Creative", "You are now in Creative Mode");
        messageyml.set("Message.gamemode.Survival", "You are now in Survival Mode");
        messageyml.set("Message.gamemode.Adventure", "You are now in Adventure Mode");
        messageyml.set("Message.gamemode.Spectator", "You are now in Spectator Mode");

        messageyml.set("Message.gamemode.setCreative", "You have set the player %targetplayer% to Creative mode");
        messageyml.set("Message.gamemode.setSurvival", "You have set the player %targetplayer% to survival mode");
        messageyml.set("Message.gamemode.setAdventure", "You have set the player %targetplayer% to Adventure mode");
        messageyml.set("Message.gamemode.setSpectator", "You have set the player %targetplayer% to Spectator mode");

        messageyml.set("Message.build.help", "Use /build [Player]");
        messageyml.set("Message.build.off", "You can't build now");
        messageyml.set("Message.build.on", "you can build now");
        messageyml.set("Message.build.seton", "The Player %targetplayer% can build now");
        messageyml.set("Message.build.setoff", "The Player %targetplayer% can't build now");

        messageyml.set("Message.fly.help", "Use /fly [Player]");
        messageyml.set("Message.fly.on", "You can now fly");
        messageyml.set("Message.fly.off", "You can't fly now");
        messageyml.set("Message.fly.seton", "The Player %targetplayer% can now fly");
        messageyml.set("Message.fly.setoff", "The Player %targetplayer% can't fly now");

        messageyml.set("Message.items.Navigator", "Navigator");
        messageyml.set("Message.items.PlayerHider", "Playerhider");
        messageyml.set("Message.items.Updatebook", "Updates");

        messageyml.set("Message.book", "# If you need more book pages, you can copy page1 or page2, but this must always correspond to the pages, otherwise errors may occur ");
        messageyml.set("Message.book.Author", "Server Team");
        messageyml.set("Message.book.Pages", 2);
        messageyml.set("Message.book.Page1", "This is your first Update Page");
        messageyml.set("Message.book.Page2", "This is your Second Update Page");

        // Speichere die Konfiguration
        try {
            messageyml.save(messagefile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Füge den Infotext am Anfang der Datei hinzu
        try {
            String content = new String(Files.readAllBytes(messagefile.toPath()), StandardCharsets.UTF_8);
            content = info + content;
            Files.write(messagefile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void Config(){
        String info = "# All die Messages Support MiniMessages https://docs.advntr.dev/minimessage/format.html";

        // Schreibe den Infotext in die Datei
        try {
            Files.write(messagefile.toPath(), info.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            configyml.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            String content = new String(Files.readAllBytes(configfile.toPath()), StandardCharsets.UTF_8);
            content = info + content;
            Files.write(configfile.toPath(), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
