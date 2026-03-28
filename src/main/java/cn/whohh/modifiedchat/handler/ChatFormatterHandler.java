package cn.whohh.modifiedchat.handler;

import cn.whohh.modifiedchat.Modifiedchat;
import cn.whohh.modifiedchat.constants.ChatConstants;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * 聊天消息格式化处理器
 */
public class ChatFormatterHandler {

    private final Modifiedchat plugin;

    public ChatFormatterHandler(Modifiedchat plugin) {
        this.plugin = plugin;
    }

    /**
     * 格式化聊天消息（使用事件对象）
     */
    public void formatAndBroadcast(Player player, String message, AsyncPlayerChatEvent event) {
        String playerName = player.getDisplayName();

        // 替换占位符
        playerName = plugin.setPlaceholders(playerName, player);
        message = plugin.setPlaceholders(message, player);

        String formattedMessage = format(playerName, message, player);

        if (plugin.isUseMiniMessage()) {
            // MiniMessage 模式：需要自己广播消息
            event.setCancelled(true);
            broadcastMiniMessage(formattedMessage);
        } else {
            // 传统模式：使用 event.setFormat()，让事件正常传递
            event.setCancelled(false);
            String translatedMessage = translateLegacyMessage(formattedMessage);
            event.setFormat(translatedMessage);
        }
    }

    /**
     * 格式化聊天消息（不使用事件对象，用于私聊）
     */
    public void formatAndBroadcast(Player player, String message) {
        String playerName = player.getDisplayName();

        // 替换占位符
        playerName = plugin.setPlaceholders(playerName, player);
        message = plugin.setPlaceholders(message, player);

        String formattedMessage = format(playerName, message, player);

        if (plugin.isUseMiniMessage()) {
            broadcastMiniMessage(formattedMessage);
        } else {
            broadcastLegacyMessage(formattedMessage);
        }
    }

    /**
     * 格式化消息文本
     */
    private String format(String playerName, String message, Player player) {
        String formattedMessage = plugin.getChatFormat()
                .replace(ChatConstants.PLACEHOLDER_DISPLAY_NAME, playerName)
                .replace(ChatConstants.PLACEHOLDER_MESSAGE, message);
        return plugin.setPlaceholders(formattedMessage, player);
    }

    /**
     * 以MiniMessage格式广播消息
     */
    private void broadcastMiniMessage(String formattedMessage) {
        try {
            // 自动清除传统格式代码以避免MiniMessage解析错误
            String cleanedMessage = cleanLegacyFormatCodes(formattedMessage);
            Component component = plugin.getMiniMessage().deserialize(cleanedMessage);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.sendMessage(component);
            }
        } catch (Exception e) {
            plugin.getLogger().warning("MiniMessage 格式化失败：" + e.getMessage());
            // 降级到传统格式
            broadcastLegacyMessage(formattedMessage);
        }
    }

    /**
     * 清除传统格式代码（& 和 § 符号）
     * 用于兼容MiniMessage（MiniMessage不支持传统格式代码）
     */
    private String cleanLegacyFormatCodes(String message) {
        if (message == null) {
            return "";
        }
        // 移除所有 & 符号的格式代码（如 &7, &f 等）
        message = message.replaceAll("&[0-9a-fk-orA-FK-OR]", "");
        // 移除所有 § 符号的格式代码
        message = message.replaceAll("§[0-9a-fk-orA-FK-OR]", "");
        return message;
    }

    /**
     * 以传统格式广播消息
     */
    private void broadcastLegacyMessage(String formattedMessage) {
        String translatedMessage = translateLegacyMessage(formattedMessage);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendMessage(translatedMessage);
        }
    }

    /**
     * 转换传统格式消息（同时支持 & 和 § 符号）
     */
    private String translateLegacyMessage(String message) {
        String translated = ChatColor.translateAlternateColorCodes('&', message);
        return ChatColor.translateAlternateColorCodes('§', translated);
    }
}
