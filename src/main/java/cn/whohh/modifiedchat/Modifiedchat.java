package cn.whohh.modifiedchat;

import cn.whohh.modifiedchat.constants.ChatConstants;
import cn.whohh.modifiedchat.handler.ChatCommandHandler;
import cn.whohh.modifiedchat.handler.ChatFormatterHandler;
import cn.whohh.modifiedchat.util.PluginInitializer;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ModifiedChat 插件主类
 * 一个功能完整的聊天格式修改插件，支持MiniMessage和PlaceholderAPI
 */
public class Modifiedchat extends JavaPlugin implements Listener {

    private String chatFormat;
    private boolean useMiniMessage;
    private MiniMessage miniMessage;
    private boolean placeholderAPIEnabled;
    
    private ChatCommandHandler commandHandler;
    private ChatFormatterHandler formatterHandler;
    private PluginInitializer initializer;

    @Override
    public void onEnable() {
        initializer = new PluginInitializer(this);
        commandHandler = new ChatCommandHandler(this);
        formatterHandler = new ChatFormatterHandler(this);

        // 检查PlaceholderAPI
        placeholderAPIEnabled = initializer.isPlaceholderAPIEnabled();

        // 为1.8版本注册事件处理器
        if (initializer.isVersion1_8()) {
            Bukkit.getPluginManager().registerEvents(new Modifiedchat_1_8(this), this);
        }

        // 加载配置
        loadConfig();
        
        // 记录启动信息
        initializer.logStartupInfo(useMiniMessage, placeholderAPIEnabled);

        // 注册事件监听器
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("ModifiedChat插件已关闭！");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();
        
        // 处理私聊命令
        if (isWhisperCommand(cmdName)) {
            commandHandler.handleWhisperCommand(sender, args);
            return true;
        }
        
        // 处理重载命令
        if (cmdName.equals(ChatConstants.CMD_RELOAD)) {
            commandHandler.handleReloadCommand(sender);
            return true;
        }
        
        return false;
    }

    /**
     * 判断是否为私聊命令
     */
    private boolean isWhisperCommand(String cmdName) {
        return cmdName.equals(ChatConstants.CMD_WHISPER) ||
               cmdName.equals(ChatConstants.CMD_TELL) ||
               cmdName.equals(ChatConstants.CMD_WHISPER_ALIAS_1) ||
               cmdName.equals(ChatConstants.CMD_WHISPER_ALIAS_2);
    }

    /**
     * 加载配置文件
     */
    public void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        
        // 加载聊天格式
        chatFormat = getConfig().getString(ChatConstants.CONFIG_CHAT_FORMAT);
        if (chatFormat == null || chatFormat.isEmpty()) {
            chatFormat = ChatConstants.DEFAULT_CHAT_FORMAT;
            getLogger().info("使用默认聊天格式");
        }
        
        // 加载MiniMessage设置
        useMiniMessage = getConfig().getBoolean(ChatConstants.CONFIG_USE_MINIMESSAGE, ChatConstants.DEFAULT_USE_MINIMESSAGE);
        if (miniMessage == null) {
            miniMessage = MiniMessage.miniMessage();
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        formatterHandler.formatAndBroadcast(player, message, event);
    }

    /**
     * 获取聊天格式
     */
    public String getChatFormat() {
        return chatFormat;
    }

    /**
     * 设置聊天格式
     */
    public void setChatFormat(String chatFormat) {
        this.chatFormat = chatFormat != null ? chatFormat : ChatConstants.DEFAULT_CHAT_FORMAT;
    }

    /**
     * 是否使用MiniMessage格式
     */
    public boolean isUseMiniMessage() {
        return useMiniMessage;
    }

    /**
     * 设置是否使用MiniMessage格式
     */
    public void setUseMiniMessage(boolean useMiniMessage) {
        this.useMiniMessage = useMiniMessage;
    }

    /**
     * 获取MiniMessage实例
     */
    public MiniMessage getMiniMessage() {
        return miniMessage;
    }

    /**
     * 处理占位符替换（支持PlaceholderAPI）
     * @param text 要处理的文本
     * @param player 玩家对象（用于PlaceholderAPI）
     * @return 处理后的文本
     */
    public String setPlaceholders(String text, Player player) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        if (placeholderAPIEnabled && player != null) {
            return PlaceholderAPI.setPlaceholders(player, text);
        }
        return text;
    }
}
