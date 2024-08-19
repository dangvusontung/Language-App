package com.tungdvs.languageapp.chat.data.datasources

import com.tungdvs.languageapp.chat.domain.entities.Message

interface ChatDataSource {
    suspend fun getMessages(): List<Message>
    suspend fun sendMessage(message: Message): Message
    suspend fun deleteMessage(messageId: String)
    suspend fun updateMessage(message: Message)
}
