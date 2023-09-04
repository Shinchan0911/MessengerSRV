package me.shinthl09.color;

import me.shinthl09.util.function;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class color {
    private static Plugin plugin = function.plugin;
    public static String transalate(String message) {
        return ChatColor.translateAlternateColorCodes('&', (message.replace("{prefix}", plugin.getConfig().getString("prefix"))));

    }
}
