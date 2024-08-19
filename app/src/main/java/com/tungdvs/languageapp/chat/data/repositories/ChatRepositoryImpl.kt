package com.tungdvs.languageapp.chat.data.repositories

import com.tungdvs.languageapp.chat.data.datasources.ChatDataSource
import com.tungdvs.languageapp.chat.domain.entities.Message
import com.tungdvs.languageapp.chat.domain.repositories.ChatRepository

class ChatRepositoryImpl(private val chatDataSource: ChatDataSource) : ChatRepository {

    override suspend fun getMessages(): List<Message> {
        return chatDataSource.getMessages()
    }

    override suspend fun sendMessage(message: Message): Message {
        return chatDataSource.sendMessage(message)
    }

    override suspend fun deleteMessage(messageId: String) {
        chatDataSource.deleteMessage(messageId)
    }

    override suspend fun updateMessage(message: Message) {
        chatDataSource.updateMessage(message)
    }
}
