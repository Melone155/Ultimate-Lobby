package de.melone.lobby.listener;

import de.melone.lobby.LobbyMain;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class Updates {

    private static List<String> pages = new ArrayList<>();

    public static void LoadeBook(){
        int counter = 1;
        int BookPages = LobbyMain.messageyml.getInt("Message.book.Pages");

        for (int i = 0; i < BookPages; i++) {
            String Pagecounter = "Message.book.Page" + counter;
            pages.add(LobbyMain.messageyml.getString(Pagecounter));
            System.out.println(Pagecounter);
            counter++;
        }
    }

    public void UpdateBook(Player player){


        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookmeta = (BookMeta) book.getItemMeta();
        bookmeta.setDisplayName(LobbyMain.messageyml.getString("Message.items.Updatebook"));
        bookmeta.setTitle(LobbyMain.messageyml.getString("Message.items.Updatebook"));
        bookmeta.setAuthor("Admin Team");

        bookmeta.addPage(pages.toArray(new String[0]));
        book.setItemMeta(bookmeta);

        player.getInventory().setItem(1, book);
    }

}
