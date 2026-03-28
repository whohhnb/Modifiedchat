package cn.whohh.modifiedchat;

import cn.whohh.modifiedchat.handler.ChatFormatterHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

/**
 * 1.8 版本兼容性监听器
 * 为 Bukkit 1.8.x 版本提供聊天事件处理
 */
public class Modifiedchat_1_8 implements Listener {

    private final Modifiedchat plugin;
    private final ChatFormatterHandler formatterHandler;

    public Modifiedchat_1_8(Plugin plugin) {
        this.plugin = (Modifiedchat) plugin;
        this.formatterHandler = new ChatFormatterHandler(this.plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        formatterHandler.formatAndBroadcast(event.getPlayer(), event.getMessage(), event);
    }
}
