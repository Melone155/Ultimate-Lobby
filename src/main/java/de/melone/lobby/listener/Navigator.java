package de.melone.lobby.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.melone.lobby.LobbyMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Navigator implements Listener {

    private static LobbyMain plugin;
    public Navigator(LobbyMain plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onNav(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.hasItem()) {
                if (event.getItem().getType() == Material.RECOVERY_COMPASS) {
                    Inventory inv = Bukkit.createInventory(null, 54, LobbyMain.messageyml.getString("Message.items.Navigator"));

                    ItemStack leer = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta leermeta = leer.getItemMeta();
                    leermeta.setDisplayName(" ");
                    leer.setItemMeta(leermeta);

                    for (int i = 0; i < 54; i++) {
                        inv.setItem(i, leer);
                    }

                    String materialName1 = LobbyMain.messageyml.getString("Message.Navigator.item1.Material");
                    Material material1 = Material.getMaterial(materialName1.toUpperCase());

                    String materialName2 = LobbyMain.messageyml.getString("Message.Navigator.item2.Material");
                    Material material2 = Material.getMaterial(materialName2.toUpperCase());

                    String materialName3 = LobbyMain.messageyml.getString("Message.Navigator.item3.Material");
                    Material material3 = Material.getMaterial(materialName3.toUpperCase());

                    String materialName4 = LobbyMain.messageyml.getString("Message.Navigator.item4.Material");
                    Material material4 = Material.getMaterial(materialName4.toUpperCase());

                    String materialName5 = LobbyMain.messageyml.getString("Message.Navigator.item5.Material");
                    Material material5 = Material.getMaterial(materialName5.toUpperCase());

                    String materialName6 = LobbyMain.messageyml.getString("Message.Navigator.item6.Material");
                    Material material6 = Material.getMaterial(materialName6.toUpperCase());

                    String materialName7 = LobbyMain.messageyml.getString("Message.Navigator.item7.Material");
                    Material material7 = Material.getMaterial(materialName7.toUpperCase());

                    String materialName8 = LobbyMain.messageyml.getString("Message.Navigator.item8.Material");
                    Material material8 = Material.getMaterial(materialName8.toUpperCase());

                    String materialName9 = LobbyMain.messageyml.getString("Message.Navigator.item9.Material");
                    Material material9 = Material.getMaterial(materialName9.toUpperCase());

                    String materialName10 = LobbyMain.messageyml.getString("Message.Navigator.item10.Material");
                    Material material10 = Material.getMaterial(materialName10.toUpperCase());

                    ItemStack item1 = new ItemStack(material1);
                    ItemMeta item1meta = item1.getItemMeta();
                    item1meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item1.Name"));
                    item1.setItemMeta(item1meta);

                    ItemStack item2 = new ItemStack(material2);
                    ItemMeta item2meta = item2.getItemMeta();
                    item2meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item2.Name"));
                    item2.setItemMeta(item2meta);

                    ItemStack item3 = new ItemStack(material3);
                    ItemMeta item3meta = item3.getItemMeta();
                    item3meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item3.Name"));
                    item3.setItemMeta(item3meta);

                    ItemStack item4 = new ItemStack(material4);
                    ItemMeta item4meta = item4.getItemMeta();
                    item4meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item4.Name"));
                    item4.setItemMeta(item4meta);

                    ItemStack item5 = new ItemStack(material5);
                    ItemMeta item5meta = item5.getItemMeta();
                    item5meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item5.Name"));
                    item5.setItemMeta(item5meta);

                    ItemStack item6 = new ItemStack(material6);
                    ItemMeta item6meta = item6.getItemMeta();
                    item6meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item6.Name"));
                    item6.setItemMeta(item6meta);

                    ItemStack item7 = new ItemStack(material7);
                    ItemMeta item7meta = item7.getItemMeta();
                    item7meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item7.Name"));
                    item7.setItemMeta(item7meta);

                    ItemStack item8 = new ItemStack(material8);
                    ItemMeta item8meta = item8.getItemMeta();
                    item8meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item8.Name"));
                    item8.setItemMeta(item8meta);

                    ItemStack item9 = new ItemStack(material9);
                    ItemMeta item9meta = item9.getItemMeta();
                    item9meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item9.Name"));
                    item9.setItemMeta(item9meta);

                    ItemStack item10 = new ItemStack(material10);
                    ItemMeta item10meta = item10.getItemMeta();
                    item10meta.setDisplayName(LobbyMain.messageyml.getString("Message.Navigator.item10.Name"));
                    item10.setItemMeta(item10meta);

                    inv.setItem(12, item2);
                    inv.setItem(13, item4);
                    inv.setItem(14, item3);
                    inv.setItem(20, item5);
                    inv.setItem(24, item6);
                    inv.setItem(28, item7);
                    inv.setItem(34, item8);
                    inv.setItem(37, item9);
                    inv.setItem(40, item1);
                    inv.setItem(43, item10);

                    player.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void onItem(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(LobbyMain.messageyml.getString("Message.items.Navigator"))) {
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item1.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item1.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item1.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item1.server");
                String item = "item1";

                Function(function, warp, server, item, player);

            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item2.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item2.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item2.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item2.server");
                String item = "item2";

                Function(function, warp, server, item, player);

            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item3.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item3.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item3.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item3.server");
                String item = "item3";

                Function(function, warp, server, item, player);

            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item4.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item4.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item4.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item4.server");
                String item = "item4";

                Function(function, warp, server, item, player);


            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item5.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item5.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item5.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item5.server");
                String item = "item5";

                Function(function, warp, server, item, player);

            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item6.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item6.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item6.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item6.server");
                String item = "item6";

                Function(function, warp, server, item, player);

            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item7.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item7.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item7.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item7.server");
                String item = "item7";

                Function(function, warp, server, item, player);

            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item8.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item8.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item8.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item8.server");
                String item = "item8";

                Function(function, warp, server, item, player);

            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item9.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item9.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item9.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item9.server");
                String item = "item9";

                Function(function, warp, server, item, player);

            } else  if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(LobbyMain.messageyml.getString("Message.Navigator.item10.Name"))) {

                String function = LobbyMain.messageyml.getString("Message.Navigator.item10.Function");
                String warp = LobbyMain.messageyml.getString("Message.Navigator.item10.warpname");
                String server = LobbyMain.messageyml.getString("Message.Navigator.item10.server");
                String item = "item10";

                Function(function, warp, server, item, player);

            }
        }
    }

    private static void Function(String function, String warp , String server , String item ,Player player){
        if (function.equals("warp")){

            Double X = LobbyMain.configyml.getDouble(warp + ".X");
            int Y = LobbyMain.configyml.getInt(warp + ".Y");
            Double Z = LobbyMain.configyml.getDouble(warp + ".Z");
            int Pitch = LobbyMain.configyml.getInt(warp + ".Pitch");
            int Yaw = LobbyMain.configyml.getInt(warp + ".Yaw");
            String Wold = LobbyMain.configyml.getString( warp + ".World");

            player.teleport(new Location(player.getServer().getWorld(Wold), X, Y, Z, Yaw, Pitch));

        } else if (function.equals("server")){

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect"); // Das lässt du so, das ist so gesehen die Aktion die ausgeführt wird -> Mit Server VERBINDEN
            out.writeUTF(server); // Freebuild-1 ist hier der Servername
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

        } else {

            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix + " " + LobbyMain.messageyml.getString("Message.Navigator." + item + ".message")));

        }
    }
}
