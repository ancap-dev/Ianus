package ru.ancap.ianus.plugin;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import ru.ancap.ianus.plugin.player.IanusControlled;

@AllArgsConstructor
public class Oculus implements Listener {

    private final ConfigurationSection limitsSection;

    @EventHandler
    public void on(PlayerJoinEvent event) {
        new IanusControlled(event.getPlayer(), limitsSection).setupLimit();
    }

    @EventHandler
    public void on(PlayerTeleportEvent event) {
        PlayerTeleportEvent.TeleportCause cause = event.getCause();
        if (cause == PlayerTeleportEvent.TeleportCause.END_PORTAL || cause == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            new IanusControlled(event.getPlayer(), limitsSection).setupLimit();
        }
    }
    
    public void register(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

}
