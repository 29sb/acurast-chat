package com.acurast.chat.data.model

data class ChatMessage(
    val id: String,
    val content: String,
    val isUser: Boolean,
    val timestamp: Long
)