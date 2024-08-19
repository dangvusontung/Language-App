package com.tungdvs.languageapp.chat.domain.entities

import java.util.UUID
import java.util.Date

data class Message(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val timestamp: Date = Date(),
    val sender: String // This could be "user" or "ai"
)
