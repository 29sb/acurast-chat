package com.acurast.chat.data.model

data class CompletionRequest(
    val prompt: String,
    val n_predict: Int = 300
)

data class CompletionResponse(
    val content: String?,
    val model: String?,
    val tokens_predicted: Int?,
    val tokens_evaluated: Int?,
    val timings: Timings?
)

data class Timings(
    val prompt_n: Int?,
    val prompt_ms: Double?,
    val predicted_n: Int?,
    val predicted_ms: Double?
)