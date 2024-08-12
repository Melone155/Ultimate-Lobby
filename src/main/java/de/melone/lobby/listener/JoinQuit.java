package de.melone.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuit implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.joinMessage(null);
    }

    @EventHandler
    public void onQuti(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.quitMessage(null);
    }
}
