package com.tungdvs.languageapp.domain.repositories

import android.telecom.Call
import com.tungdvs.languageapp.data.model.api.chatcompletion.ChatCompletionResponse
import com.tungdvs.languageapp.data.model.api.chatcompletion.Message
import kotlinx.coroutines.flow.Flow

interface OpenAIRepository {
    fun createChatCompletion(model: String, messages: List<Message>): Flow<Result<String>>
}
