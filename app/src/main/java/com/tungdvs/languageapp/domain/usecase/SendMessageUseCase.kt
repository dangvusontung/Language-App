package com.tungdvs.languageapp.domain.usecase

import com.tungdvs.languageapp.data.model.api.chatcompletion.Message
import com.tungdvs.languageapp.domain.repositories.OpenAIRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val openAIRepository: OpenAIRepository
) {
    fun sendMessage(model: String, messages: List<Message>) = openAIRepository.createChatCompletion(model, messages)


}
