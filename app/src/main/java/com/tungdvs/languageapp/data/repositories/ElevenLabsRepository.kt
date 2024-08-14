package com.tungdvs.languageapp.data.repositories

import com.tungdvs.languageapp.data.api.ElevenLabsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.await

class ElevenLabsRepository(private val elevenLabsApiService: ElevenLabsApiService) {

    fun synthesizeSpeech(voiceId: String, text: String): Flow<Result<ResponseBody>> = flow {
        try {
            val jsonBody = JSONObject().apply {
                put("text", text)
            }
            val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaTypeOrNull())

            val response = elevenLabsApiService.textToSpeech(voiceId, requestBody).await()
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
