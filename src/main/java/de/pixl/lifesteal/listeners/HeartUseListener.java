package de.pixl.lifesteal.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.awt.*;

public class HeartUseListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        ItemStack heart = player.getInventory().getItemInMainHand();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (heart.getItemMeta().getLocalizedName().equals("heart")) {

                player.sendMessage("§5You gained one extra Heart!");

                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.DARK_PURPLE +
                        "You Gained §e+1 §5 Heart! "));

                player.playSound(player, Sound.ITEM_TOTEM_USE, 0.3f, 1.0f);

                player.getInventory().removeItem(heart);


                Inventory inventory = player.getInventory();
                ItemStack itemStack = new ItemStack(heart.getType(), 1);

                // Remove one item of the type from the player's inventory
                inventory.removeItem(itemStack);

                AttributeInstance maxHealthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                double newMaxHealth = maxHealthAttribute.getBaseValue() + 2;
                maxHealthAttribute.setBaseValue(newMaxHealth);
                player.setHealth(newMaxHealth);
            }
        }
    }
}
