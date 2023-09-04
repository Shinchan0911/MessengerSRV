package me.shinthl09.util;

import com.google.gson.JsonObject;
import me.shinthl09.MessengerSRV;
import org.bukkit.plugin.Plugin;

import java.text.MessageFormat;

public class api {
    private static final Plugin plugin = MessengerSRV.getPlugin(MessengerSRV.class);
    public static JsonObject SendMessage(String content, Boolean type) {
        int threadId = (type) ? plugin.getConfig().getInt("thread-id.admin") : plugin.getConfig().getInt("thread-id.global");
        String url = plugin.getConfig().getString("url-bot") + "/sendmessage?content=" + function.EncodeURL(content) + "&token=" + plugin.getConfig().getString("token") + "&threadid=" + threadId;
        JsonObject response = function.SendRequest(url);
        return response;
    }
}
