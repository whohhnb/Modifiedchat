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
     * 初始化MiniMessage实例
     */
    public MiniMessage initMiniMessage() {
        return MiniMessage.miniMessage();
    }

    /**
     * 记录启动信息和版本兼容性提示
     */
    public void logStartupInfo(boolean minimessageEnabled, boolean placeholderAPIEnabled) {
        String version = getServerVersion();
        plugin.getLogger().info(ChatConstants.PLUGIN_NAME + " 插件已启动！(版本: " + version + ")");
        plugin.getLogger().info("支持 Minecraft 1.8 ~ 最新版本");
        if (minimessageEnabled) {
            plugin.getLogger().info("MiniMessage 已启用！");
        } else {
            plugin.getLogger().info("使用传统的 ChatColor 格式");
        }
        if (placeholderAPIEnabled) {
            plugin.getLogger().info("PlaceholderAPI 已检测到，占位符支持已启用!");
        }
    }

    /**
     * 获取服务器版本字符串
     */
    private String getServerVersion() {
        try {
            String fullVersion = Bukkit.getVersion();
            // 尝试提取版本号 (格式: git-Paper-<hash> (MC: x.xx.x))
            if (fullVersion.contains("MC: ")) {
                return fullVersion.substring(fullVersion.indexOf("MC: ") + 4, fullVersion.lastIndexOf(")"));
            }
            return fullVersion;
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
