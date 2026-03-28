package cn.whohh.modifiedchat.handler;

import cn.whohh.modifiedchat.Modifiedchat;
import cn.whohh.modifiedchat.constants.ChatConstants;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 聊天命令处理器
 */
public class ChatCommandHandler {

    private final Modifiedchat plugin;

    public ChatCommandHandler(Modifiedchat plugin) {
        this.plugin = plugin;
    }

    /**
     * 处理私聊命令
     */
    public void handleWhisperCommand(CommandSender sender, String[] args) {
        if (!sender.hasPermission("modifiedchat.whisper")) {
            sender.sendMessage(ChatColor.RED + "你没有权限执行此命令！");
            return;
        }
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + String.format(ChatConstants.ERROR_USAGE, ChatConstants.CMD_WHISPER));
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            sender.sendMessage(ChatColor.RED + String.format(ChatConstants.ERROR_PLAYER_NOT_FOUND, args[0]));
            return;
        }

        String message = buildMessage(args, 1);
        message = ChatColor.stripColor(message);

        Player senderPlayer = sender instanceof Player ? (Player) sender : null;
        sendWhisperMessage(sender, target, senderPlayer, message);
    }

    /**
     * 发送私聊消息
     */
    private void sendWhisperMessage(CommandSender sender, Player target, Player senderPlayer, String message) {
        String displayName = senderPlayer != null ? senderPlayer.getDisplayName() : sender.getName();
        displayName = plugin.setPlaceholders(displayName, senderPlayer);
        message = plugin.setPlaceholders(message, senderPlayer);

        String formattedMessage = plugin.getChatFormat()
                .replace(ChatConstants.PLACEHOLDER_DISPLAY_NAME, displayName)
                .replace(ChatConstants.PLACEHOLDER_MESSAGE, message);
        formattedMessage = plugin.setPlaceholders(formattedMessage, senderPlayer);

        if (plugin.isUseMiniMessage()) {
            Component component = plugin.getMiniMessage().deserialize(formattedMessage);
            if (senderPlayer != null) {
                senderPlayer.sendMessage(component);
            } else {
                String legacyMsg = translateLegacyMessage(
                        ChatConstants.PREFIX_PRIVATE_CHAT + ChatConstants.PREFIX_SEND + target.getDisplayName() + ChatConstants.PREFIX_RECEIVE + message);
                sender.sendMessage(legacyMsg);
            }
            target.sendMessage(component);
        } else {
            String legacyMsg1 = translateLegacyMessage(
                    ChatConstants.PREFIX_PRIVATE_CHAT + ChatConstants.PREFIX_SEND + target.getDisplayName() + ChatConstants.PREFIX_RECEIVE + message);
            sender.sendMessage(legacyMsg1);
            String legacyMsg2 = translateLegacyMessage(
                    ChatConstants.PREFIX_PRIVATE_CHAT + sender.getName() + ChatConstants.PREFIX_RECEIVE + message);
            target.sendMessage(legacyMsg2);
        }
    }

    /**
     * 处理重载命令
     */
    public void handleReloadCommand(CommandSender sender) {
        if (!sender.hasPermission("modifiedchat.reload")) {
            sender.sendMessage(ChatColor.RED + "你没有权限执行此命令！");
            return;
        }
        plugin.reloadConfig();
        plugin.loadConfig();
        sender.sendMessage(ChatColor.GREEN + PLUGIN_NAME + " 配置文件已重载!");
    }

    /**
     * 构建消息字符串
     */
    private String buildMessage(String[] args, int startIndex) {
        StringBuilder messageBuilder = new StringBuilder();
        for (int i = startIndex; i < args.length; i++) {
            if (i > startIndex) {
                messageBuilder.append(" ");
            }
            messageBuilder.append(args[i]);
        }
        return messageBuilder.toString();
    }

    /**
     * 转换传统格式消息（同时支持 & 和 § 符号）
     */
    private String translateLegacyMessage(String message) {
        String translated = ChatColor.translateAlternateColorCodes('&', message);
        return ChatColor.translateAlternateColorCodes('§', translated);
    }

    private static final String PLUGIN_NAME = ChatConstants.PLUGIN_NAME;
}
