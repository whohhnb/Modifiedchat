package cn.whohh.modifiedchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Modifiedchat extends JavaPlugin implements Listener {

    private String chatFormat;

    @Override
    public void onEnable() {
        getLogger().info("ModifiedChat插件已启动！");
        // 获取服务器版本，格式为 x.y.z
        String version = Bukkit.getVersion().split(" ")[1];

        // 如果服务器版本为 1.8.x 或以下，则注册 Bukkit API 的相关事件
        if (version.startsWith("1.8.")) {
            Bukkit.getPluginManager().registerEvents(new Modifiedchat_1_8(this), this);
        }

        loadConfig();
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("ModifiedChat插件已关闭！");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("modifiedwhisper") || cmd.getName().equalsIgnoreCase("modifiedtell") || cmd.getName().equalsIgnoreCase("mt") || cmd.getName().equalsIgnoreCase("ms")){
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "用法: /modified" + cmd.getName() + " <玩家> <信息>");
                return true;
            }

            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "玩家 " + args[0].toString() + " 未找到。");
                return true;
            }


            String message = "";
            for (int i = 1; i < args.length; i++) {
                message = message + args[i] + " ";
            }

            message = ChatColor.stripColor(message);
            String formattedMessage = chatFormat
                    .replace("{DISPLAY_NAME}", sender instanceof Player ? ((Player) sender).getDisplayName() : sender.getName())
                    .replace("{MESSAGE}", message);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[私聊] &f你对 " + target.getDisplayName() + " 说: " + message));
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[私聊] " + sender.getName() + " 对你说: " + message));
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("mtreload")) {
            reloadConfig();
            loadConfig();
            sender.sendMessage(ChatColor.GREEN + "ModifiedChat 配置文件已重载!");
            return true;
        }
        return false;
    }

    public void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        chatFormat = getConfig().getString("chat-format");
        if (chatFormat == null) {
            chatFormat = "&7[&f{DISPLAY_NAME}&7] &f{MESSAGE}";
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getDisplayName();
        String message = event.getMessage();
        String formattedMessage = chatFormat
                .replace("{DISPLAY_NAME}", playerName)
                .replace("{MESSAGE}", message);
        event.setFormat(ChatColor.translateAlternateColorCodes('&', formattedMessage));
    }

    public String getChatFormat() {
        return chatFormat;
    }

    public void setChatFormat(String chatFormat) {
        this.chatFormat = chatFormat;
    }
}
