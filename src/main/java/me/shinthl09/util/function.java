package me.shinthl09.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.clip.placeholderapi.PlaceholderAPI;
import me.shinthl09.MessengerSRV;
import me.shinthl09.event.MessengerChatListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import spark.Spark;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class function {
    public static Plugin plugin = MessengerSRV.getPlugin(MessengerSRV.class);

    public static FileConfiguration Messages;
    public static File file;

    public static Spark app;
    public static JsonObject SendRequest(String url) {
        try {
            CookieHandler.setDefault(new CookieManager((CookieStore) null, CookiePolicy.ACCEPT_ALL));
            HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoInput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = (String) reader.lines().collect(Collectors.joining());
            return (JsonObject) (new JsonParser()).parse(response);
        } catch (SocketTimeoutException var4) {
            plugin.getLogger().info("Read timed out, may API server go down");
            plugin.getLogger().info("Attemping to try again...");
            return null;
        } catch (Exception var5) {
            return null;
        }
    }

    public static String EncodeURL(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public static String replacePlaceholders(Player player, String text) {
        Plugin placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        if (placeholderAPI == null || !placeholderAPI.isEnabled()) {
            return text;
        }

        return PlaceholderAPI.setPlaceholders(player, text);
    }

    public static void LoadMessages(String language) {
        File folder = new File(plugin.getDataFolder() + File.separator + "messages");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        file = new File(plugin.getDataFolder() + File.separator + "messages" + File.separator + language + ".yml");
        Messages = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void ReloadMessages() {
        Messages = YamlConfiguration.loadConfiguration(file);
    }

    public static void startApiServer() {
        Spark.port(5000);
        MessengerChatListener.onMessengerChat();
        app.awaitInitialization();
        plugin.getLogger().info("&aPlugin is running on port " + plugin.getConfig().getString("port"));
    }

    public static void stopApiServer() {
        app.stop();
    }

}
