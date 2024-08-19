package com.tungdvs.languageapp.data.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ElevenLabsApiService {
    @POST("v1/text-to-speech/{voiceId}")
    @Headers(
        "Content-Type: application/json",
        "xi-api-key: ${System.getenv("ELEVEN_LAB_API")}"
    )
    fun textToSpeech(
        @Path("voiceId") voiceId: String,
        @Body requestBody: RequestBody
    ): Call<ResponseBody>
}
