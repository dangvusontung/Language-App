package com.tungdvs.languageapp.data.model.api.chatcompletion

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIService {
    @POST("v1/chat/completions")
    fun createChatCompletion(@Body request: ChatCompletionRequest): Call<ChatCompletionResponse>
}
