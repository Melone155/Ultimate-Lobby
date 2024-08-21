package de.melone.lobby.listener;

import de.melone.lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerHide implements Listener {

    public static ArrayList<UUID> hider = new ArrayList<>();
    private final LobbyMain plugin;

    private static ArrayList<String> hide = new ArrayList<String>();
    private static ArrayList<String> showlore = new ArrayList<String>();

    public PlayerHide(LobbyMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player =  event.getPlayer();

        if (event.getItem() != null) {
            if (event.getHand() == EquipmentSlot.HAND) {
                if (event.getItem().getType().equals(Material.LIME_DYE) || event.getItem().getType().equals(Material.GRAY_DYE)) {

                    if (hider.contains(player.getPlayer().getUniqueId())) {

                        hider.remove(player.getPlayer().getUniqueId());

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            player.showPlayer(plugin, players);
                        }

                        ItemStack show = new ItemStack(Material.GRAY_DYE);
                        ItemMeta showmeta = show.getItemMeta();
                        showmeta.setDisplayName(LobbyMain.messageyml.getString("Message.Playerhider.ON.item"));
                        showmeta.setLore(showlore);
                        show.setItemMeta(showmeta);

                        player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.Playerhider.ON.Message")));
                        Bukkit.getScheduler().runTask(plugin, ()->  player.getInventory().setItem(8, show));

                    } else {


                        hider.add(player.getPlayer().getUniqueId());

                        for (Player players : Bukkit.getOnlinePlayers()) {
                            player.hidePlayer(plugin, players);
                        }

                        ItemStack hider = new ItemStack(Material.LIME_DYE);
                        ItemMeta hidermeta = hider.getItemMeta();
                        hidermeta.setDisplayName(LobbyMain.messageyml.getString("Message.Playerhider.Off.item"));
                        hidermeta.setLore(hide);
                        hider.setItemMeta(hidermeta);

                        player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.Playerhider.Off.Message")));
                        Bukkit.getScheduler().runTask(plugin, ()->  player.getInventory().setItem(8, hider));

                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void loreset(){
        if (hide.isEmpty()){
            hide.add(LobbyMain.messageyml.getString("Message.Playerhider.Off.lore"));
        }

        if (showlore.isEmpty()){
            showlore.add(LobbyMain.messageyml.getString("Message.Playerhider.ON.lore"));
        }
    }
}
