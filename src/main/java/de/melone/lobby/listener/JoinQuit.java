package de.melone.lobby.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinQuit implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        event.joinMessage(null);

        LobbyItems(player);
    }

    @EventHandler
    public void onQuti(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.quitMessage(null);
    }

    public void LobbyItems(Player player){

        player.getInventory().clear();

        ItemStack nav = new ItemStack(Material.RECOVERY_COMPASS);
        ItemMeta navmeta = nav.getItemMeta();
        navmeta.setDisplayName("§6§lNavigator");
        nav.setItemMeta(navmeta);

        ItemStack hide = new ItemStack(Material.GRAY_DYE);
        ItemMeta hidemeta = hide.getItemMeta();
        hidemeta.setDisplayName("Spieler Verstecken");
        hide.setItemMeta(hidemeta);

        ItemStack friends = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta friendsmeta = friends.getItemMeta();
        friendsmeta.setDisplayName("Freunde");
        friends.setItemMeta(friendsmeta);

        player.getInventory().setItem(0, nav);
        new Updates().UpdateBook(player);
        player.getInventory().setItem(7, hide);
        player.getInventory().setItem(8, friends);

    }
}
