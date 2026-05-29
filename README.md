# Acurast Chat - 去中心化 AI 聊天应用

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Release](https://img.shields.io/badge/Release-v1.0.0-green.svg)](https://github.com/29sb/acurast-chat/releases/tag/v1.0.0)

**一个基于 Acurast 网络的去中心化 AI 聊天应用**

你的消息通过全球手机节点网络进行处理，保护隐私安全。

---

## 🌍 English Version

[English README](README_EN.md)

---

## ✨ 特性

- 🌐 **去中心化架构** - 基于 Acurast 网络的手机节点进行 LLM 推理
- 🔒 **隐私保护** - 消息在 TEE（可信执行环境）中处理，节点无法查看内容
- 💬 **实时对话** - 流畅的聊天体验，支持消息历史记录
- ⚙️ **灵活配置** - 支持 Acurast 节点和本地演示模式切换
- 🎨 **Material Design 3** - 现代化 UI 设计，支持深色模式

## 📱 功能

### 聊天界面
- 实时消息发送和接收
- 消息气泡样式（用户消息 vs AI 回复）
- 时间戳显示
- 加载状态指示器

### 设置页面
- 连接模式选择（Acurast 节点 / 本地演示）
- 节点 URL 配置
- 连接测试功能

### Acurast 集成
- 支持 Acurast LLM 节点通信
- 兼容 `proxy.acu.run` 隧道代理
- 自动降级到本地演示模式

## 🚀 快速开始

### 下载安装

从 [Releases](https://github.com/29sb/acurast-chat/releases) 页面下载最新 APK：

```bash
# 下载 v1.0.0
wget https://github.com/29sb/acurast-chat/releases/download/v1.0.0/AcurastChat-v1.0.0-release.apk
```

### 从源码编译

```bash
# 克隆仓库
git clone https://github.com/29sb/acurast-chat.git
cd acurast-chat

# 编译 release 版本
./gradlew assembleRelease

# APK 位置
# app/build/outputs/apk/release/app-release.apk
```

### 安装到设备

```bash
# 复制到设备
adb install app/build/outputs/apk/release/app-release.apk

# 或者直接打开 APK 文件安装
```

## 🏗️ 技术架构

### 技术栈
- **语言**: Kotlin 2.1.0
- **UI 框架**: Jetpack Compose + Material Design 3
- **架构**: MVVM
- **网络**: OkHttp 4.12
- **JSON**: Gson 2.11
- **导航**: Navigation Compose 2.9.0

### 项目结构
```
acurast-chat/
├── app/
│   ├── src/main/
│   │   ├── java/com/acurast/chat/
│   │   │   ├── MainActivity.kt
│   │   │   ├── data/
│   │   │   │   ├── model/         # 数据模型
│   │   │   │   └── repository/    # 数据仓库
│   │   │   └── ui/
│   │   │       ├── navigation/    # 导航
│   │   │       ├── screens/       # 页面
│   │   │       └── theme/         # 主题
│   │   └── res/
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle/
```

### 核心组件

#### 1. 数据层
- `ChatMessage` - 消息数据模型
- `CompletionRequest/Response` - LLM API 请求/响应模型
- `ChatRepository` - 网络请求处理，支持 Acurast 节点和本地模拟

#### 2. UI 层
- `ChatScreen` - 主聊天界面
- `SettingsScreen` - 设置页面
- `AcurastChatTheme` - Material Design 3 主题

#### 3. 网络通信
- 支持 Acurast LLM 节点（`/completion` 接口）
- 自动降级到本地模拟响应
- 超时和错误处理

## 🔧 配置说明

### Acurast 节点

默认节点 URL: `https://proxy.acu.run/{设备地址}`

要使用真实的 Acurast LLM 节点：
1. 在 Acurast 网络上部署 LLM 服务
2. 获取节点的隧道地址
3. 在设置页面配置节点 URL

### 本地演示模式

当 Acurast 节点不可用时，App 会自动降级到本地演示模式，返回模拟的 AI 响应。

## 📖 Acurast 相关

### 什么是 Acurast？

Acurast 是一个基于智能手机的去中心化计算网络：
- 📱 **260,000+ 手机节点** - 全球分布的计算资源
- 🔐 **TEE 安全执行** - 可信执行环境保障隐私
- 🤖 **LLM 推理** - 支持在手机上运行 AI 模型
- 💰 **ACU 代币** - 节点运营商获得奖励

### 相关资源
- [Acurast 官网](https://acurast.com)
- [Acurast 文档](https://docs.acurast.com)
- [Acurast 示例应用](https://github.com/Acurast/acurast-example-apps)
- [Acurast CLI](https://github.com/Acurast/acurast-cli)

## 🤝 贡献

欢迎贡献代码！请遵循以下步骤：
1. Fork 本仓库
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 🙏 致谢

- [Acurast](https://acurast.com) - 去中心化计算网络
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android UI 工具包
- [Material Design 3](https://m3.material.io/) - 设计系统

## 📞 联系方式

- GitHub: [@29sb](https://github.com/29sb)
- 项目链接: [https://github.com/29sb/acurast-chat](https://github.com/29sb/acurast-chat)

---

**用 ❤️ 和 Acurast 构建**