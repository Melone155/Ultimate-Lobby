package de.melone.lobby.cmd;

import de.melone.lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class CMD_fly implements CommandExecutor {

    private static ArrayList<UUID> fly = new ArrayList<>();
    private static Player targetplayer;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("lobby.fly")){
            if (args.length == 0){
                if (fly.contains(player.getUniqueId())){
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.fly.off")));
                    player.setFlying(false);
                } else {
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.fly.on")));
                    player.setFlying(true);
                }


            } else if (args.length == 1){
                targetplayer = Bukkit.getPlayer(args[0]);

                if (fly.contains(targetplayer.getUniqueId())){
                    targetplayer.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.fly.off")));
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.fly.setoff")));
                    targetplayer.setFlying(false);
                } else {
                    targetplayer.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.fly.on")));
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.fly.seton")));
                    targetplayer.setFlying(true);
                }
            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize("Message.fly.help"));
            }
        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.noperms));
        }
        return false;
    }

    private static String ConfigMessages(String message) {
        if (message.contains("%targetplayer%")) {
            return message.replace("%targetplayer%", targetplayer.getName());
        }
        return message;
    }
}
