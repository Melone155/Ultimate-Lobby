package de.melone.lobby.cmd;

import de.melone.lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CMD_gm implements CommandExecutor, TabCompleter {

    private static Player targetplayer;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("gm")){
            if (player.hasPermission("lobby.gm") || player.isOp()){

                if (args.length == 1){
                    if (args[0].equalsIgnoreCase("0")){
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.Survival"))));

                    } else if (args[0].equalsIgnoreCase("1")){
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.Creative"))));

                    }else if (args[0].equalsIgnoreCase("2")){
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.Adventure"))));

                    }else if (args[0].equalsIgnoreCase("3")){
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.Spectator"))));
                    }
                }

                if (args.length == 2){

                        targetplayer = Bukkit.getPlayer(args[1]);

                        if (args[0].equalsIgnoreCase("0")){
                            targetplayer.setGameMode(GameMode.SURVIVAL);
                            targetplayer.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.gamemode.Survival")));
                            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + ConfigMessages(Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.setSurvival")))));

                        } else if (args[0].equalsIgnoreCase("1")){
                            targetplayer.setGameMode(GameMode.CREATIVE);
                            targetplayer.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.Creative"))));
                            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + ConfigMessages(Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.setCreative")))));

                        }else if (args[0].equalsIgnoreCase("2")){
                            targetplayer.setGameMode(GameMode.SPECTATOR);
                            targetplayer.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.Adventure"))));
                            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + ConfigMessages(Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.setSpectator")))));

                        }else if (args[0].equalsIgnoreCase("3")){
                            targetplayer.setGameMode(GameMode.ADVENTURE);
                            targetplayer.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.Spectator"))));
                            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + ConfigMessages(Objects.requireNonNull(LobbyMain.messageyml.getString("Message.gamemode.setAdventure")))));

                        } else {

                            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.help"))));
                        }

                } else if(args.length == 0){
                    player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + Objects.requireNonNull(LobbyMain.messageyml.getString("Message.help"))));
                }

            } else {
                player.sendMessage(LobbyMain.prefix + LobbyMain.noperms);
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();

        if (args.length == 1){
            list.add("0");
            list.add("1");
            list.add("2");
            list.add("3");
        }

        if (args.length == 2){
            for (Player online : Bukkit.getOnlinePlayers()){
                list.add(online.getName());
            }
        }


        return list;
    }

    private static String ConfigMessages(String message) {
        if (message.contains("%targetplayer%")) {
            return message.replace("%targetplayer%", targetplayer.getName());
        }
        return message;
    }
}
