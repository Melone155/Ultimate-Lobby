package de.melone.lobby.cmd;

import de.melone.lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CMD_warp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("lobby.warp")){
            if (args.length == 1){

                String warpname = args[0];

                int X = (int) player.getLocation().getX();
                int Y = (int) player.getLocation().getY();
                int Z = (int) player.getLocation().getZ();

                double Yaw = player.getLocation().getYaw();
                double Pitch = player.getLocation().getPitch();

                double Xdouble = X + 0.5;
                double Ydouble = Y + 0.5;

                String world = player.getLocation().getWorld().getName();;

                LobbyMain.configyml.set(warpname + ".X", Xdouble);
                LobbyMain.configyml.set(warpname+ ".Y", Ydouble);
                LobbyMain.configyml.set(warpname + ".Z", Z);
                LobbyMain.configyml.set(warpname + ".Yaw", Yaw);
                LobbyMain.configyml.set(warpname + ".Pitch", Pitch);
                LobbyMain.configyml.set(warpname + ".World", Pitch);

                try {
                    LobbyMain.configyml.save(LobbyMain.configfile);
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.setspawn")));
                } catch (IOException e) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.error")));
                    player.sendMessage(" \n \n" + e);
                    System.out.println(e);
                }

            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.help")));
            }
        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.noperms));
        }
        return false;
    }
}
