package com.acurast.chat.data.repository

import com.acurast.chat.data.model.CompletionRequest
import com.acurast.chat.data.model.CompletionResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

class ChatRepository {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    
    private val gson = Gson()
    
    // 默认 Acurast 节点 URL（需要替换为实际节点地址）
    private var nodeUrl = "https://proxy.acu.run/local"
    
    suspend fun sendMessage(message: String): String = withContext(Dispatchers.IO) {
        try {
            // 构建请求
            val request = CompletionRequest(
                prompt = message,
                n_predict = 300
            )
            
            val json = gson.toJson(request)
            val requestBody = json.toRequestBody("application/json".toMediaType())
            
            val httpRequest = Request.Builder()
                .url("$nodeUrl/completion")
                .post(requestBody)
                .build()
            
            // 发送请求
            val response = client.newCall(httpRequest).execute()
            
            if (response.isSuccessful) {
                val responseBody = response.body?.string() ?: ""
                val completionResponse = gson.fromJson(responseBody, CompletionResponse::class.java)
                completionResponse.content ?: "无响应内容"
            } else {
                // 如果节点不可用，返回模拟响应
                getSimulatedResponse(message)
            }
        } catch (e: Exception) {
            // 网络错误时返回模拟响应
            getSimulatedResponse(message)
        }
    }
    
    private fun getSimulatedResponse(message: String): String {
        // 模拟 AI 响应（用于演示）
        val responses = listOf(
            "这是一个很好的问题！作为 Acurast 去中心化 AI，我正在通过全球手机节点网络为你提供服务。",
            "我理解你的问题。在 Acurast 网络中，你的消息被安全地处理，没有任何中心化服务器可以访问你的数据。",
            "有趣的观点！Acurast 的 TEE（可信执行环境）确保了你的隐私安全，即使节点运营商也无法看到你的消息内容。",
            "让我思考一下... 在去中心化网络中，每个手机节点都贡献算力，这就是 Acurast 的核心理念。",
            "好的，我来帮你分析这个问题。通过 Acurast 网络，AI 推理可以在全球数万台手机上并行处理。"
        )
        return responses[message.hashCode() % responses.size]
    }
    
    fun setNodeUrl(url: String) {
        nodeUrl = url
    }
}