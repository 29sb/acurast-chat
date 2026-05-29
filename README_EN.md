# English Version

# Acurast Chat - Decentralized AI Chat Application

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

**A decentralized AI chat application based on the Acurast network**

Your messages are processed through a global network of mobile nodes, ensuring privacy and security.

## ✨ Features

- 🌐 **Decentralized Architecture** - LLM inference powered by Acurast network mobile nodes
- 🔒 **Privacy Protection** - Messages processed in TEE (Trusted Execution Environment), nodes cannot view content
- 💬 **Real-time Chat** - Smooth chat experience with message history
- ⚙️ **Flexible Configuration** - Switch between Acurast nodes and local demo mode
- 🎨 **Material Design 3** - Modern UI design with dark mode support

## 📱 Features

### Chat Interface
- Real-time message sending and receiving
- Message bubble styling (user messages vs AI replies)
- Timestamp display
- Loading state indicator

### Settings Page
- Connection mode selection (Acurast node / Local demo)
- Node URL configuration
- Connection testing functionality

### Acurast Integration
- Support for Acurast LLM node communication
- Compatible with `proxy.acu.run` tunnel proxy
- Automatic fallback to local demo mode

## 🚀 Quick Start

### Compile APK

```bash
# Clone repository
git clone https://github.com/29sb/acurast-chat.git
cd acurast-chat

# Compile debug version
./gradlew assembleDebug

# APK location
# app/build/outputs/apk/debug/app-debug.apk
```

### Install to Device

```bash
# Copy to device
adb install app/build/outputs/apk/debug/app-debug.apk

# Or directly open the APK file to install
```

## 🏗️ Technical Architecture

### Tech Stack
- **Language**: Kotlin 2.1.0
- **UI Framework**: Jetpack Compose + Material Design 3
- **Architecture**: MVVM
- **Networking**: OkHttp 4.12
- **JSON**: Gson 2.11
- **Navigation**: Navigation Compose 2.9.0

### Project Structure
```
acurast-chat/
├── app/
│   ├── src/main/
│   │   ├── java/com/acurast/chat/
│   │   │   ├── MainActivity.kt
│   │   │   ├── data/
│   │   │   │   ├── model/         # Data models
│   │   │   │   └── repository/    # Data repositories
│   │   │   └── ui/
│   │   │       ├── navigation/    # Navigation
│   │   │       ├── screens/       # Screens
│   │   │       └── theme/         # Theme
│   │   └── res/
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle/
```

### Core Components

#### 1. Data Layer
- `ChatMessage` - Message data model
- `CompletionRequest/Response` - LLM API request/response models
- `ChatRepository` - Network request handling, supports Acurast nodes and local simulation

#### 2. UI Layer
- `ChatScreen` - Main chat interface
- `SettingsScreen` - Settings page
- `AcurastChatTheme` - Material Design 3 theme

#### 3. Network Communication
- Support for Acurast LLM nodes (`/completion` endpoint)
- Automatic fallback to local simulated responses
- Timeout and error handling

## 🔧 Configuration

### Acurast Nodes

Default node URL: `https://proxy.acu.run/{device_address}`

To use real Acurast LLM nodes:
1. Deploy LLM service on Acurast network
2. Obtain node tunnel address
3. Configure node URL in settings page

### Local Demo Mode

When Acurast nodes are unavailable, the app automatically falls back to local demo mode, returning simulated AI responses.

## 📖 About Acurast

### What is Acurast?

Acurast is a decentralized computing network based on smartphones:
- 📱 **260,000+ Mobile Nodes** - Globally distributed computing resources
- 🔐 **TEE Secure Execution** - Trusted Execution Environment ensures privacy
- 🤖 **LLM Inference** - Support for running AI models on mobile devices
- 💰 **ACU Token** - Node operators receive rewards

### Related Resources
- [Acurast Official Website](https://acurast.com)
- [Acurast Documentation](https://docs.acurast.com)
- [Acurast Example Apps](https://github.com/Acurast/acurast-example-apps)
- [Acurast CLI](https://github.com/Acurast/acurast-cli)

## 🤝 Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## 🙏 Acknowledgments

- [Acurast](https://acurast.com) - Decentralized computing network
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android UI toolkit
- [Material Design 3](https://m3.material.io/) - Design system

## 📞 Contact

- GitHub: [@29sb](https://github.com/29sb)
- Project Link: [https://github.com/29sb/acurast-chat](https://github.com/29sb/acurast-chat)

---

**Built with ❤️ and Acurast**