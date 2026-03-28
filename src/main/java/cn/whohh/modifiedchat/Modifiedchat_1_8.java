package cn.whohh.modifiedchat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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

        if (((Modifiedchat) plugin).isUseMiniMessage()) {
            // 使用 MiniMessage 格式化
            MiniMessage miniMessage = ((Modifiedchat) plugin).getMiniMessage();
            Component component = miniMessage.deserialize(formattedMessage);
            event.setFormat("");
            // 为所有在线玩家发送消息
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.sendMessage(component);
            }
        } else {
            // 使用传统的 ChatColor 格式
            event.setFormat(ChatColor.translateAlternateColorCodes('&', formattedMessage));
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        }
    }
}
