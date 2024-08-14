package com.tungdvs.languageapp.data.repositories

import com.tungdvs.languageapp.data.model.api.chatcompletion.ChatCompletionRequest
import com.tungdvs.languageapp.data.model.api.chatcompletion.ChatCompletionResponse
import com.tungdvs.languageapp.data.model.api.chatcompletion.Message
import com.tungdvs.languageapp.data.model.api.chatcompletion.OpenAIService
import com.tungdvs.languageapp.domain.repositories.OpenAIRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await

class OpenAIRepositoryImpl(private val openAIService: OpenAIService) : OpenAIRepository {

    override fun createChatCompletion(model: String, messages: List<Message>): Flow<Result<String>> = flow {
        try {
            val request = ChatCompletionRequest(model = model, messages = messages)
            val response = openAIService.createChatCompletion(request).await()
            val content = response.choices.firstOrNull()?.message?.content ?: ""
            emit(Result.success(content))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
