# ModifiedChat

<div align="center">

[![GitHub License](https://img.shields.io/badge/license-MIT-blue)](https://github.com/whohhnb/Modifiedchat/blob/main/LICENSE)
![GitHub stars](https://img.shields.io/github/stars/whohhnb/Modifiedchat)
![Java version](https://img.shields.io/badge/Java-17%2B-brightgreen)
![Minecraft version](https://img.shields.io/badge/Minecraft-1.8%20~%201.20%2B-brightgreen)

一个功能强大、轻量级的Minecraft聊天格式修改插件

[简体中文](#features) | [English](./README_EN.md)

</div>

---

## ✨ 功能特性

- 🎨 **自定义聊天格式** - 灵活修改玩家聊天消息样式
- 🌈 **MiniMessage 支持** - 支持高级文本格式化（RGB颜色、样式效果等）
- 🔌 **PlaceholderAPI 集成** - 支持通过占位符显示动态数据
- 👤 **私聊功能** - 玩家之间的私聊消息发送
- 📝 **配置即插** - 简单直观的 YAML 配置方式
- 🔄 **热重载** - 无需重启服务器即可重载配置
- 🖥️ **多版本兼容** - 支持 Minecraft 1.8 ~ 1.20+
- 📱 **Folia 适配** - 完全支持 Folia 服务器
- ⚡ **性能优化** - 轻量级设计，对服务器性能无影响

---

## 🔧 前置要求

| 要求项 | 版本 |
|--------|------|
| **Java** | 17 或更高 |
| **Minecraft Server** | 1.8 ~ 1.20.1+ |
| **PlaceholderAPI**（可选） | 2.11.5+ |

> ⚠️ **注意**：PlaceholderAPI 是可选的，即使没有安装也不会影响插件功能

---

## 📥 安装步骤

### 1. 下载插件
从 [Releases](https://github.com/whohhnb/Modifiedchat/releases) 页面下载最新版本的 JAR 文件

### 2. 放置文件
将下载的 JAR 文件放入服务器的 `plugins` 文件夹

### 3. 启动服务器
启动服务器，插件会自动生成默认配置文件

### 4. 配置插件
编辑生成的 `plugins/modifiedchat/config.yml` 文件

### 5. 重载插件
在游戏中执行 `/mtreload` 命令重载配置，或重启服务器

---

## ⚙️ 配置说明

### 基础配置

编辑 `config.yml` 文件：

```yaml
# 聊天消息格式
# 可用变量：{DISPLAY_NAME}（玩家显示名）、{MESSAGE}（消息内容）
chat-format: "&7[&f{DISPLAY_NAME}&7] &f{MESSAGE}"

# 是否启用 MiniMessage 格式（更丰富的文本格式化）
use-minimessage: true
```

### 聊天格式示例

#### 传统格式（使用 & 代码）
```yaml
chat-format: "&7[&f{DISPLAY_NAME}&7] &f{MESSAGE}"
# 效果：[玩家名] 消息
```

#### MiniMessage 格式（RGB 颜色）
```yaml
chat-format: "<color:#00FF00>[<color:#FFFF00>{DISPLAY_NAME}<color:#00FF00>] <color:white>{MESSAGE}"
# 效果：[玩家名] 消息（使用RGB颜色）
```

#### 集成 PlaceholderAPI
```yaml
chat-format: "<color:#00FF00>[<color:#FFFF00>{DISPLAY_NAME} <color:#00AA00>%vault_money%G<color:#00FF00>] <color:white>{MESSAGE}"
# 显示：[玩家名 金币数量] 消息
```

### MiniMessage 标签参考

| 标签 | 说明 | 示例 |
|------|------|------|
| `<color:#RRGGBB>` | RGB 颜色 | `<color:#FF0000>红色` |
| `<color:red>` | 命名颜色 | `<color:red>红色` |
| `<bold>` | 粗体 | `<bold>粗体文本</bold>` |
| `<italic>` | 斜体 | `<italic>斜体文本</italic>` |
| `<underlined>` | 下划线 | `<underlined>下划线</underlined>` |
| `<strikethrough>` | 删除线 | `<strikethrough>删除线</strikethrough>` |
| `<hover:show_text:'悬停提示'>` | 悬停效果 | `<hover:show_text:'提示'>文本</hover>` |
| `<click:open_url:'链接'>` | 链接点击 | `<click:open_url:'https://...'>点击</click>` |

---

## 🎮 命令用法

### 私聊命令

发送私聊消息给其他玩家

```bash
# 命令 1
/modifiedtell <玩家> <信息>

# 命令 2
/modifiedwhisper <玩家> <信息>

# 简化命令 1
/mt <玩家> <信息>

# 简化命令 2
/ms <玩家> <信息>
```

**使用示例**
```bash
/mt whohh_ 你好呀！
/modifiedtell Admin 请问有时间吗？
/modifiedwhisper Steve 今天天气不错
```

### 重载命令

无需重启服务器，直接重载配置文件

```bash
/mtreload
```

---

## 🏷️ 占位符支持

### 内置占位符

| 占位符 | 说明 |
|--------|------|
| `{DISPLAY_NAME}` | 玩家的显示名称 |
| `{MESSAGE}` | 玩家发送的消息 |

### PlaceholderAPI 占位符

安装 PlaceholderAPI 后，可使用更多占位符（取决于已安装的扩展）

**常用占位符示例**

| 占位符 | 说明 | 前置条件 |
|--------|------|----------|
| `%player_name%` | 玩家名称 | PlaceholderAPI |
| `%player_level%` | 玩家等级 | PlaceholderAPI + Leveler |
| `%vault_money%` | 玩家金币 | PlaceholderAPI + Vault + 经济插件 |
| `%playtimeminus_days%` | 游玩天数 | PlaceholderAPI + PlayTime |
| `%vaultgroup%` | 玩家权限组 | PlaceholderAPI + Vault + 权限插件 |

**示例配置**
```yaml
chat-format: "&7[&f{DISPLAY_NAME} &a💰%vault_money%&7] &f{MESSAGE}"
# 显示：[玩家名 💰1000] 消息
```

---

## ❓ 常见问题

### Q: 插件需要什么前置？
**A:** 插件本身不需要前置。PlaceholderAPI 是可选的，用于支持占位符功能。

### Q: 如何启用 MiniMessage 格式？
**A:** 在 `config.yml` 中设置 `use-minimessage: true`，然后使用 MiniMessage 语法即可。

### Q: PlaceholderAPI 占位符不显示？
**A:** 
1. 确保已安装 PlaceholderAPI 插件
2. 确保对应的扩展已安装（如需要 Vault 支持，则需安装 Vault 插件）
3. 执行 `/mtreload` 重载配置

### Q: 如何修改私聊样式？
**A:** 编辑 `config.yml` 中的 `chat-format` 配置项，修改后执行 `/mtreload` 即可生效。

### Q: 支持哪些 Minecraft 版本？
**A:** 支持 1.8 ~ 1.20.1+ 的 Minecraft 服务器，包括 Paper、Spigot 和 Folia。

### Q: 如何报告 Bug？
**A:** 在 [GitHub Issues](https://github.com/whohhnb/Modifiedchat/issues) 页面报告问题。

---

## 📖 项目结构

```
ModifiedChat/
├── src/main/java/cn/whohh/modifiedchat/
│   ├── Modifiedchat.java           # 主类
│   ├── Modifiedchat_1_8.java       # 1.8 兼容性
│   ├── constants/
│   │   └── ChatConstants.java      # 常量管理
│   ├── handler/
│   │   ├── ChatCommandHandler.java     # 命令处理
│   │   └── ChatFormatterHandler.java   # 消息格式化
│   └── util/
│       └── PluginInitializer.java  # 初始化工具
├── src/main/resources/
│   ├── plugin.yml                  # 插件配置
│   └── config.yml                  # 默认配置
└── pom.xml                         # Maven 配置
```

详见 [OPTIMIZATION.md](./OPTIMIZATION.md) 了解代码优化细节

---

## 📊 性能指标

- **插件大小**：< 500KB
- **启动时间**：< 100ms
- **内存占用**：< 10MB
- **CPU 影响**：可忽略

---

## 📜 许可证

本项目采用 [MIT 许可证](./LICENSE)

---

## 🙏 致谢

- [PaperMC](https://papermc.io/) - 优秀的服务器实现
- [Adventure](https://github.com/KyoriPowered/adventure) - MiniMessage 库
- [PlaceholderAPI](https://wiki.placeholderapi.com/) - 占位符支持

---

## ⭐ 支持项目

如果你觉得这个项目有帮助，不妨给个 Star ⭐

**感谢你的支持！** 🎉
