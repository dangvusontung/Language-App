package com.tungdvs.languageapp.chat.domain.repositories

import com.tungdvs.languageapp.chat.domain.entities.Message

interface ChatRepository {
    suspend fun getMessages(): List<Message>
    suspend fun sendMessage(message: Message): Message
    suspend fun deleteMessage(messageId: String)
    suspend fun updateMessage(message: Message)
}
