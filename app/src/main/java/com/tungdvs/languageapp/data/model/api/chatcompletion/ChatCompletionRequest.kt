package com.tungdvs.languageapp.data.model.api.chatcompletion

data class ChatCompletionRequest(
    val model: String,
    val messages: List<Message>,
)

data class Message(
    val role: String,
    val content: String,
)