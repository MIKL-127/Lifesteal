package de.pixl.lifesteal.listeners;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        Player player = e.getEntity().getPlayer();

        ItemStack heart = new ItemStack(Material.RED_DYE);
        ItemMeta heartMeta = heart.getItemMeta();
        assert heartMeta != null;
        heartMeta.setDisplayName("§cHeart");
        heartMeta.setLocalizedName("heart");

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Right click to gain one extra heart!");
        lore.add(ChatColor.RED + "ONLY SPEND 1 PER USE!");
        heartMeta.setLore(lore);

        heart.setItemMeta(heartMeta);

        Location deathLocation = player.getLocation();
        World world = deathLocation.getWorld();
        world.dropItemNaturally(deathLocation, heart);


        AttributeInstance maxHealthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        double newMaxHealth = maxHealthAttribute.getBaseValue() - 2;

        if (newMaxHealth < 1) {
            BanList banList = Bukkit.getBanList(BanList.Type.NAME);
            banList.addBan(player.getName(), "You have been banned for 1 day for having too low health.", Date.from(Instant.now().plus(1, ChronoUnit.SECONDS)), null);
            player.kickPlayer("§cYou have been banned for 1 day for having too low health.");
        } else {
            maxHealthAttribute.setBaseValue(newMaxHealth);
            player.setHealth(20);
        }

    }
}
