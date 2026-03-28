package cn.whohh.modifiedchat.util;

import cn.whohh.modifiedchat.constants.ChatConstants;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 插件初始化工具类
 */
public class PluginInitializer {

    private final JavaPlugin plugin;

    public PluginInitializer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * 检查PlaceholderAPI是否已加载
     */
    public boolean isPlaceholderAPIEnabled() {
        return Bukkit.getPluginManager().getPlugin(ChatConstants.PLUGIN_PLACEHOLDERAPI) != null;
    }

    /**
     * 检查是否为1.8版本
     */
    public boolean isVersion1_8() {
        try {
            String version = Bukkit.getVersion().split(" ")[1];
            return version.startsWith(ChatConstants.VERSION_1_8);
        } catch (Exception e) {
            plugin.getLogger().warning("无法检测服务器版本：" + e.getMessage());
            return false;
        }
    }

    /**
     * 初始化MiniMessage实例
     */
    public MiniMessage initMiniMessage() {
        return MiniMessage.miniMessage();
    }

    /**
     * 记录启动信息
     */
    public void logStartupInfo(boolean minimessageEnabled, boolean placeholderAPIEnabled) {
        plugin.getLogger().info(ChatConstants.PLUGIN_NAME + " 插件已启动！");
        if (minimessageEnabled) {
            plugin.getLogger().info("MiniMessage 已启用！");
        } else {
            plugin.getLogger().info("使用传统的 ChatColor 格式");
        }
        if (placeholderAPIEnabled) {
            plugin.getLogger().info("PlaceholderAPI 已检测到，占位符支持已启用!");
        }
    }
}
