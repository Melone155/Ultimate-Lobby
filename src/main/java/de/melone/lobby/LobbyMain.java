package de.melone.lobby;

import com.mojang.brigadier.Command;
import de.melone.lobby.cmd.*;
import de.melone.lobby.listener.*;
import de.melone.lobby.ulti.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;


public final class LobbyMain extends JavaPlugin {

    File folder = new File("plugins/Lobby");

    public static File messagefile = new File("plugins//Lobby//Messages.yml");
    public static YamlConfiguration messageyml = YamlConfiguration.loadConfiguration(messagefile);

    public static File configfile = new File("plugins//Lobby//Config.yml");
    public static YamlConfiguration configyml = YamlConfiguration.loadConfiguration(configfile);

    public static String prefix;
    public static String noperms = messageyml.getString("Message.noperms");
    public static String updatemessage;

    public static LobbyMain plugin;

    @Override
    public void onEnable() {

        registerconfig();
        registercommands();
        registerlistener();

        Updates.LoadeBook();

        prefix = messageyml.getString("Message.prefix");

        PlayerHide.loreset();

        new UpdateChecker(this, 118897).getVersion(version -> {
            if (!this.getDescription().getVersion().equals(version)) {
                Bukkit.getConsoleSender().sendMessage("§4Update for the Plugin Ultimate Lobby is now available");
                Bukkit.getConsoleSender().sendMessage("§4https://www.spigotmc.org/resources/ultimate-lobby.118897/");
                updatemessage = "§7Update for the Plugin Ultimate Lobby is now available \n Downloade Now <click:open_url:'https://docs.advntr.dev/minimessage'>Click here</click>";
            }
        });

        plugin = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

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
        getCommand("setwarp").setExecutor(new CMD_warp());
    }

    private void registerlistener() {
        final PluginManager pluginManager = super.getServer().getPluginManager();

        pluginManager.registerEvents(new Buildlistener(), this);
        pluginManager.registerEvents(new JoinQuit(), this);
        pluginManager.registerEvents(new CancelledEvent(), this);
        pluginManager.registerEvents(new Navigator(this), this);
        pluginManager.registerEvents(new PlayerHide(this), this);
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
        messageyml.set("Info", "# All die Messages Support MiniMessages https://docs.advntr.dev/minimessage/format.html");

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

        messageyml.set("Message.Playerhider.ON.item", "Player Hiden");
        messageyml.set("Message.Playerhider.ON.Message", "All players are now visible");
        messageyml.set("Message.Playerhider.ON.lore", "Click to hide players");
        messageyml.set("Message.Playerhider.Off.item", "Player displayed");
        messageyml.set("Message.Playerhider.Off.Message", "All players are now invisible");
        messageyml.set("Message.Playerhider.Off.lore", "All players are now invisible");

        messageyml.set("Message.Navigator.item1.Name", "Spawn");
        messageyml.set("Message.Navigator.item1.Material", "NETHER_STAR");
        messageyml.set("Message.Navigator.item1.Function", "warp");
        messageyml.set("Message.Navigator.item1.server", "server");
        messageyml.set("Message.Navigator.item1.warpname", "Spawn");
        messageyml.set("Message.Navigator.item1.message", "This is a message");

        messageyml.set("Message.Navigator.item2.Name", "Item2");
        messageyml.set("Message.Navigator.item2.Material", "BARRIER");
        messageyml.set("Message.Navigator.item2.Function", "server");
        messageyml.set("Message.Navigator.item2.server", "server");
        messageyml.set("Message.Navigator.item2.warpname", "Spawn");
        messageyml.set("Message.Navigator.item2.message", "This is a message");

        messageyml.set("Message.Navigator.item3.Name", "Item3");
        messageyml.set("Message.Navigator.item3.Material", "BARRIER");
        messageyml.set("Message.Navigator.item3.Function", "funktion");
        messageyml.set("Message.Navigator.item3.server", "server");
        messageyml.set("Message.Navigator.item3.warpname", "Spawn");
        messageyml.set("Message.Navigator.item3.message", "This is a message");

        messageyml.set("Message.Navigator.item4.Name", "Item4");
        messageyml.set("Message.Navigator.item4.Material", "BARRIER");
        messageyml.set("Message.Navigator.item4.Function", "funktion");
        messageyml.set("Message.Navigator.item4.server", "server");
        messageyml.set("Message.Navigator.item4.warpname", "Spawn");
        messageyml.set("Message.Navigator.item4.message", "This is a message");

        messageyml.set("Message.Navigator.item5.Name", "Item5");
        messageyml.set("Message.Navigator.item5.Material", "BARRIER");
        messageyml.set("Message.Navigator.item5.Function", "funktion");
        messageyml.set("Message.Navigator.item5.server", "server");
        messageyml.set("Message.Navigator.item5.warpname", "Spawn");
        messageyml.set("Message.Navigator.item5.message", "This is a message");

        messageyml.set("Message.Navigator.item6.Name", "Item6");
        messageyml.set("Message.Navigator.item6.Material", "BARRIER");
        messageyml.set("Message.Navigator.item6.Function", "funktion");
        messageyml.set("Message.Navigator.item6.server", "server");
        messageyml.set("Message.Navigator.item6.warpname", "Spawn");
        messageyml.set("Message.Navigator.item6.message", "This is a message");

        messageyml.set("Message.Navigator.item7.Name", "Item7");
        messageyml.set("Message.Navigator.item7.Material", "BARRIER");
        messageyml.set("Message.Navigator.item7.Function", "funktion");
        messageyml.set("Message.Navigator.item7.server", "server");
        messageyml.set("Message.Navigator.item7.warpname", "Spawn");
        messageyml.set("Message.Navigator.item7.message", "This is a message");

        messageyml.set("Message.Navigator.item8.Name", "Item8");
        messageyml.set("Message.Navigator.item8.Material", "BARRIER");
        messageyml.set("Message.Navigator.item8.Function", "funktion");
        messageyml.set("Message.Navigator.item8.server", "server");
        messageyml.set("Message.Navigator.item8.warpname", "Spawn");
        messageyml.set("Message.Navigator.item8.message", "This is a message");

        messageyml.set("Message.Navigator.item9.Name", "Item9");
        messageyml.set("Message.Navigator.item9.Material", "BARRIER");
        messageyml.set("Message.Navigator.item9.Function", "funktion");
        messageyml.set("Message.Navigator.item9.server", "server");
        messageyml.set("Message.Navigator.item9.warpname", "Spawn");
        messageyml.set("Message.Navigator.item9.message", "This is a message");

        messageyml.set("Message.Navigator.item10.Name", "Item10");
        messageyml.set("Message.Navigator.item10.Material", "BARRIER");
        messageyml.set("Message.Navigator.item10.Function", "funktion");
        messageyml.set("Message.Navigator.item10.server", "server");
        messageyml.set("Message.Navigator.item10.warpname", "Spawn");
        messageyml.set("Message.Navigator.item10.message", "This is a message");

        messageyml.set("Message.help", "/setwarp <Name>");

        try {
            messageyml.save(messagefile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void Config(){
        String info = "# All die Messages Support MiniMessages https://docs.advntr.dev/minimessage/format.html";

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

    public static LobbyMain getPlugin(){
        return plugin;
    }
}
