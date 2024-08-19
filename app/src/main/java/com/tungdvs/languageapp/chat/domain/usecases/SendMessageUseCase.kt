package com.tungdvs.languageapp.chat.domain.usecases

import com.tungdvs.languageapp.chat.domain.entities.Message
import com.tungdvs.languageapp.chat.domain.repositories.ChatRepository

class SendMessageUseCase(private val chatRepository: ChatRepository) {
    suspend operator fun invoke(message: Message): Message {
        return try {
            chatRepository.sendMessage(message)
        } catch (e: Exception) {
            // Handle the error (e.g., log it, return a default message, or rethrow)
            throw e
        }
    }
}
