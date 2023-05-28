# Modifiedchat

一个可以修改聊天格式的一个Minecraft Server插件，以及适配于Folia

支持1.8-1.19+

如果你喜欢简约点的，就用这款插件吧！

-----------

使用了不同的 API 和事件注册，取决于服务器的版本。相应的 API 已被放置在单独的类文件中（例如 ModifiedChat_1_8.java、ModifiedChat_1_9_to_1_12.java），它们分别实现了不同版本之间的差异。

# config.yml

在 {DISPLAY_NAME} 和 {MESSAGE} 变量中，{DISPLAY_NAME} 代表玩家的显示名称，{MESSAGE} 代表玩家发送的消息。这些变量的值将在触发 AsyncPlayerChatEvent 事件时由插件自动填充。

![image](https://github.com/whohhnb/Modifiedchat/assets/108384401/a18b4e6f-035e-4236-81d2-24090c061dda)

![image](https://github.com/whohhnb/Modifiedchat/assets/108384401/cd835d91-8ab2-4702-89eb-fb6dbcd49343)

后续会支持 PlaceholderAPI（有空吧）
# 使用

在游戏中可以使用 /modifiedtell 来私聊玩家

用法为：/modifiedtell [玩家] [信息]

例：/modifiedtell whohh_ awa


又或者是


/modifiedwhisper whohh_ awa

当然也可以简化指令：

/mt [玩家] [信息]

例：/mt whohh_ awa


重载指令为：

/mtreload

效果如下：

![image](https://github.com/whohhnb/Modifiedchat/assets/108384401/02393ff1-849d-455c-b976-80a69384a9e4)

![image](https://github.com/whohhnb/Modifiedchat/assets/108384401/a6e508ea-c111-43dd-9923-a67d751aec1f)

-----------
不妨给我点个Star？谢谢谢谢好人一生平安！
