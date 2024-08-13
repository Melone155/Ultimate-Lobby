package de.melone.lobby.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class CancelledEvent implements Listener {

    @EventHandler
    public void onleaves(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event){
        event.setCancelled(true);
    }
}
