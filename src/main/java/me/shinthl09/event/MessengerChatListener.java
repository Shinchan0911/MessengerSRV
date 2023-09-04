package me.shinthl09.event;

import com.google.gson.JsonObject;
import me.shinthl09.color.color;
import me.shinthl09.util.function;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MessengerChatListener {

    private static Plugin plugin = function.plugin;
    public static void onMessengerChat() {
        function.app.get("/sendmessage", (request, response) -> {
            String content = request.queryParams("content");
            String token = request.queryParams("token");
            String username = request.queryParams("username");
            if (content == null || token == null || username == null) {
                response.status(401);
                JsonObject errorResponse = new JsonObject();
                errorResponse.addProperty("status", 401);
                errorResponse.addProperty("errorcode", 0);
                errorResponse.addProperty("error", "Missing Data");
                return errorResponse.toString();
            }
            if (!token.equals(plugin.getConfig().getString("token"))) {
                response.status(401);
                JsonObject errorResponse = new JsonObject();
                errorResponse.addProperty("status", 401);
                errorResponse.addProperty("errorcode", 1);
                errorResponse.addProperty("error", "Invalid token");
                return errorResponse.toString();
            }

            Bukkit.broadcastMessage(color.transalate(function.Messages.getString("format-message.to-minecraft")).replace("{username}", username).replace("{message}", content));

            response.status(200);
            JsonObject successResponse = new JsonObject();
            successResponse.addProperty("status", 200);
            successResponse.addProperty("message", "Message has send");
            return successResponse.toString();
        });
    }

}
