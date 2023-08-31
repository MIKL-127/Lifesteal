package de.pixl.lifesteal.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        player.sendMessage("§aHey §e" + player.getDisplayName() + " §a! Welcome on Hypixl!");


        Bukkit.broadcastMessage("§e" + player.getDisplayName() + " §a has joined the server!");
    }
}
