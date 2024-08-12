package de.melone.lobby;

import com.mojang.brigadier.Command;
import de.melone.lobby.cmd.CMD_gm;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public final class LobbyMain extends JavaPlugin {

    File folder = new File("plugins/Lobby");

    public static File messagefile = new File("plugins//Lobby//Messages.yml");
    public static YamlConfiguration messageyml = YamlConfiguration.loadConfiguration(messagefile);

    public static String prefix = messageyml.getString("Message.prefix");
    public static String noperms = messageyml.getString("Message.noperms");

    @Override
    public void onEnable() {

        registercommands();
        registerlistener();
        registerconfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registercommands() {
        getCommand("gm").setExecutor(new CMD_gm());
    }

    private void registerlistener() {
        final PluginManager pluginManager = super.getServer().getPluginManager();
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
    }

    private void NoSQLConfig(){

    }

    private void MessageConfig(){
        messageyml.set(".", "#All die Messages Support MiniMessages \n https://docs.advntr.dev/minimessage/format.html\n \n");

        messageyml.set("Message.prefix","[Prefix]");
        messageyml.set("Message.noperms", "You have no permissions for this Command");

        messageyml.set("Message.help", "Use /gm <0,1,2,3> [Player]");

        messageyml.set("Message.gamemode.Creative", "You are now in Creative Mode");
        messageyml.set("Message.gamemode.Survival", "You are now in Survival Mode");
        messageyml.set("Message.gamemode.Adventure", "You are now in Adventure Mode");
        messageyml.set("Message.gamemode.Spectator", "You are now in Spectator Mode");

        messageyml.set("Message.gamemode.setCreative", "You have set the player %targetplayer% to Creative mode");
        messageyml.set("Message.gamemode.setSurvival", "YYou have set the player %targetplayer% to survival mode");
        messageyml.set("Message.gamemode.setAdventure", "You have set the player %targetplayer% to Adventure mode");
        messageyml.set("Message.gamemode.setSpectator", "You have set the player %targetplayer% to Spectator mode");

        messageyml.set("Message.build.off", "You can`t build now");
        messageyml.set("Message.build.on", "you can build now");
        messageyml.set("Message.build.seton", "The Player %targetplayer% can build now");
        messageyml.set("Message.build.setoff", "The Player %targetplayer% can`t build now");

        try {
            messageyml.save(messagefile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
