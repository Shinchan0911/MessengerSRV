package me.shinthl09;

import me.shinthl09.event.PlayerChatListener;
import me.shinthl09.util.function;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.shinthl09.color.color;

import java.util.ArrayList;
import java.util.List;

public final class MessengerSRV extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        function.LoadMessages(getConfig().getString("messages"));
        function.startApiServer();
        this.getLogger().info(color.transalate("&e---------------------"));
        this.getLogger().info(color.transalate(""));
        this.getLogger().info(color.transalate("&aAuthor:&e ShinTHL09"));
        this.getLogger().info(color.transalate("&aPlugin:&e Has Enable"));
        this.getLogger().info(color.transalate("&aName:&e " + this.getName()));
        this.getLogger().info(color.transalate(""));
        this.getLogger().info(color.transalate("&e---------------------"));
    }

    @Override
    public void onDisable() {
        function.stopApiServer();
        this.getLogger().info(color.transalate("&e---------------------"));
        this.getLogger().info(color.transalate(""));
        this.getLogger().info(color.transalate("&aAuthor:&e ShinTHL09"));
        this.getLogger().info(color.transalate("&aPlugin:&e Has Disable"));
        this.getLogger().info(color.transalate("&aName:&e " + this.getName()));
        this.getLogger().info(color.transalate(""));
        this.getLogger().info(color.transalate("&e---------------------"));
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("messengersrv")) {
            if (sender.hasPermission("messengersrv.*") || !(sender instanceof Player)) {
                if (args.length == 0) {
                    sender.sendMessage(color.transalate("{prefix} &eLệnh Không Hợp Lệ"));
                } else if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    this.reloadConfig();
                    function.ReloadMessages();
                    sender.sendMessage(color.transalate("{prefix} &aReload Thành Công"));
                } else {
                    sender.sendMessage(color.transalate("{prefix} &eLệnh Không Hợp Lệ"));
                }
            } else
                sender.sendMessage(color.transalate("{prefix} &eLệnh Không Hợp Lệ"));
        }
        return true;
    }
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("messengersrv")) {
            if (args.length == 1) {
                ArrayList<String> command = new ArrayList<>();
                command.add("reload");
                return command;
            }
        }
        return null;
    }
}
