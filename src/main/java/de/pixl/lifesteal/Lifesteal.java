package de.pixl.lifesteal;

import de.pixl.lifesteal.listeners.ConnectionListener;
import de.pixl.lifesteal.listeners.DeathListener;
import de.pixl.lifesteal.listeners.HeartUseListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public final class Lifesteal extends JavaPlugin {


    private static Lifesteal instance;

    @Override
    public void onEnable() {

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ConnectionListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new HeartUseListener(), this);
    }

    public static String getPrefix() {
        return ChatColor.GRAY + "[" + ChatColor.BLUE + "Lifesteal" + ChatColor.GRAY + "] ";
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static Lifesteal getInstance() {
        return instance;
    }
}
