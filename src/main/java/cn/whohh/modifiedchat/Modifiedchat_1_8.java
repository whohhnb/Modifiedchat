package cn.whohh.modifiedchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class Modifiedchat_1_8 implements Listener {

    private Plugin plugin;

    public Modifiedchat_1_8(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String format = ((Modifiedchat) plugin).getChatFormat();
        String playerName = event.getPlayer().getDisplayName();
        String message = event.getMessage();
        String formattedMessage = format
                .replace("{DISPLAY_NAME}", playerName)
                .replace("{MESSAGE}", message);

        event.setFormat(ChatColor.translateAlternateColorCodes('&', message));

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

}
