package me.shinthl09.event;

import me.shinthl09.util.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class PlayerChatListener implements Listener {
    private static Plugin plugin = function.plugin;
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        api.SendMessage(function.replacePlaceholders(player, function.Messages.getString("format-message.to-messenger").replace("{username}", player.getName()).replace("{message}", message)), false);
    }
}
