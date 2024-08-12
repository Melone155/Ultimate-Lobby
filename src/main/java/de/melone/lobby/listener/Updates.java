package de.melone.lobby.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Updates {

    public void UpdateBook(Player player){

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookmeta = (BookMeta) book.getItemMeta();
        bookmeta.setDisplayName("Updates");
        bookmeta.setTitle("Updates");
        bookmeta.setAuthor("Admin Team");
        bookmeta.setPages("§lUpdates vom DD.MM.JJJJ \n \n§rEs sind noch keine Relevate Update verfügbar");
        book.setItemMeta(bookmeta);

        player.getInventory().setItem(1, book);
    }

}
