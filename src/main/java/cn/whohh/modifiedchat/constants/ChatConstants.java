package cn.whohh.modifiedchat.constants;

/**
 * 聊天插件常量类
 */
public final class ChatConstants {

    private ChatConstants() {
        throw new AssertionError("不能实例化常量类");
    }

    // 插件名称和版本
    public static final String PLUGIN_NAME = "ModifiedChat";

    // 命令
    public static final String CMD_WHISPER = "modifiedwhisper";
    public static final String CMD_TELL = "modifiedtell";
    public static final String CMD_WHISPER_ALIAS_1 = "mt";
    public static final String CMD_WHISPER_ALIAS_2 = "ms";
    public static final String CMD_RELOAD = "mtreload";

    // 配置项
    public static final String CONFIG_CHAT_FORMAT = "chat-format";
    public static final String CONFIG_USE_MINIMESSAGE = "use-minimessage";

    // 默认值
    public static final String DEFAULT_CHAT_FORMAT = "&7[&f{DISPLAY_NAME}&7] &f{MESSAGE}";
    public static final boolean DEFAULT_USE_MINIMESSAGE = true;

    // 占位符
    public static final String PLACEHOLDER_DISPLAY_NAME = "{DISPLAY_NAME}";
    public static final String PLACEHOLDER_MESSAGE = "{MESSAGE}";

    // 信息前缀
    public static final String PREFIX_PRIVATE_CHAT = "&7[私聊] ";
    public static final String PREFIX_SEND = "&f你对 ";
    public static final String PREFIX_RECEIVE = " 对你说: ";

    // 错误信息
    public static final String ERROR_USAGE = "用法: /%s <玩家> <信息>";
    public static final String ERROR_PLAYER_NOT_FOUND = "玩家 %s 未找到。";
    public static final String ERROR_INVALID_ARGS = "应提供至少2个参数。";

    // 服务器版本
    public static final String VERSION_1_8 = "1.8.";

    // 第三方插件
    public static final String PLUGIN_PLACEHOLDERAPI = "PlaceholderAPI";
}
