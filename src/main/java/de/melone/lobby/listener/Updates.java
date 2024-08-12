package de.melone.lobby.listener;

import de.melone.lobby.LobbyMain;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Updates {

    public void UpdateBook(Player player){

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookmeta = (BookMeta) book.getItemMeta();
        bookmeta.setDisplayName(LobbyMain.messageyml.getString("Message.items.Updatebook"));
        bookmeta.setTitle(LobbyMain.messageyml.getString("Message.items.Updatebook"));
        bookmeta.setAuthor("Admin Team");

        int counter = 1;
        int BookPages = LobbyMain.messageyml.getInt("Message.book.Pages");
        String Pagecounter = "Message.book.Page" + counter;

        for (int i = 0; i < BookPages; i++) {
            bookmeta.setPages(LobbyMain.messageyml.getString(Pagecounter));
            counter++;
        }

        book.setItemMeta(bookmeta);

        player.getInventory().setItem(1, book);
    }

}
