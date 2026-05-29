package com.acurast.chat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    var nodeUrl by remember { mutableStateOf("https://proxy.acu.run/") }
    var selectedMode by remember { mutableStateOf("acurast") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("设置", fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
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
                .padding(16.dp)
        ) {
            // 连接模式
            Text(
                text = "连接模式",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedMode == "acurast",
                    onClick = { selectedMode = "acurast" },
                    label = { Text("Acurast 节点") }
                )
                
                FilterChip(
                    selected = selectedMode == "local",
                    onClick = { selectedMode = "local" },
                    label = { Text("本地演示") }
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // 节点配置
            if (selectedMode == "acurast") {
                Text(
                    text = "Acurast 节点配置",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                OutlinedTextField(
                    value = nodeUrl,
                    onValueChange = { nodeUrl = it },
                    label = { Text("节点 URL") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "示例: https://proxy.acu.run/{设备地址}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = { /* 测试连接 */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("测试连接")
                }
            } else {
                Text(
                    text = "本地演示模式",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "使用公开的 AI API 进行演示",
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "• 无需配置节点\n• 响应速度快\n• 适合功能演示",
                            fontSize = 14.sp
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // 关于
            Text(
                text = "关于",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Acurast Chat v1.0.0",
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "去中心化 AI 聊天应用\n基于 Acurast 网络的手机节点进行 LLM 推理",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "你的消息在手机节点的 TEE 中处理，保护隐私安全",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}