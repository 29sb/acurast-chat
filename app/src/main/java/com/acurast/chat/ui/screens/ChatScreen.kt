package com.acurast.chat.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acurast.chat.data.model.ChatMessage
import com.acurast.chat.data.repository.ChatRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    onNavigateToSettings: () -> Unit
) {
    val repository = remember { ChatRepository() }
    val messages = remember { mutableStateListOf<ChatMessage>() }
    var inputText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    
    // 添加欢迎消息
    LaunchedEffect(Unit) {
        if (messages.isEmpty()) {
            messages.add(
                ChatMessage(
                    id = "welcome",
                    content = "你好！我是 Acurast 去中心化 AI 助手。你的消息将通过 Acurast 网络的手机节点进行处理，保护你的隐私。有什么我可以帮你的吗？",
                    isUser = false,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Acurast Chat", fontWeight = FontWeight.Bold)
                        Text(
                            "去中心化 AI 对话",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "设置")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 消息列表
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(messages) { message ->
                    MessageBubble(message = message)
                }
            }
            
            // 加载指示器
            if (isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
            
            // 输入框
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(24.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    placeholder = { Text("输入消息...") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    maxLines = 4
                )
                
                IconButton(
                    onClick = {
                        if (inputText.isNotBlank() && !isLoading) {
                            val userMessage = ChatMessage(
                                id = System.currentTimeMillis().toString(),
                                content = inputText,
                                isUser = true,
                                timestamp = System.currentTimeMillis()
                            )
                            messages.add(userMessage)
                            val currentInput = inputText
                            inputText = ""
                            isLoading = true
                            
                            coroutineScope.launch {
                                try {
                                    val response = repository.sendMessage(currentInput)
                                    messages.add(
                                        ChatMessage(
                                            id = System.currentTimeMillis().toString(),
                                            content = response,
                                            isUser = false,
                                            timestamp = System.currentTimeMillis()
                                        )
                                    )
                                } catch (e: Exception) {
                                    messages.add(
                                        ChatMessage(
                                            id = System.currentTimeMillis().toString(),
                                            content = "错误: ${e.message}",
                                            isUser = false,
                                            timestamp = System.currentTimeMillis()
                                        )
                                    )
                                } finally {
                                    isLoading = false
                                }
                            }
                        }
                    },
                    enabled = inputText.isNotBlank() && !isLoading
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Send,
                        contentDescription = "发送",
                        tint = if (inputText.isNotBlank()) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    val alignment = if (message.isUser) Alignment.End else Alignment.Start
    val bubbleColor = if (message.isUser) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondaryContainer
    }
    val textColor = if (message.isUser) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }
    
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (message.isUser) 16.dp else 4.dp,
                        bottomEnd = if (message.isUser) 4.dp else 16.dp
                    )
                )
                .background(bubbleColor)
                .padding(12.dp)
        ) {
            Text(
                text = message.content,
                color = textColor,
                fontSize = 16.sp
            )
        }
        
        Text(
            text = formatTimestamp(message.timestamp),
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
    return sdf.format(java.util.Date(timestamp))
}