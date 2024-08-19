package com.tungdvs.languageapp.chat.domain.usecases

import com.tungdvs.languageapp.chat.domain.entities.Message
import com.tungdvs.languageapp.chat.domain.repositories.ChatRepository

class GetMessagesUseCase(private val chatRepository: ChatRepository) {
    suspend operator fun invoke(): List<Message> {
        return chatRepository.getMessages()
    }
}
