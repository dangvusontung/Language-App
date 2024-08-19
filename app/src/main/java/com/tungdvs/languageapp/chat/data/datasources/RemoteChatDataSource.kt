package com.tungdvs.languageapp.chat.data.datasources

import com.tungdvs.languageapp.chat.domain.entities.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

class RemoteChatDataSource(private val api: ChatApi) : ChatDataSource {

    override suspend fun getMessages(): List<Message> = withContext(Dispatchers.IO) {
        api.getMessages()
    }

    override suspend fun sendMessage(message: Message): Message = withContext(Dispatchers.IO) {
        api.sendMessage(message)
    }

    override suspend fun deleteMessage(messageId: String) = withContext(Dispatchers.IO) {
        api.deleteMessage(messageId)
    }

    override suspend fun updateMessage(message: Message) = withContext(Dispatchers.IO) {
        api.updateMessage(message.id, message)
    }

    interface ChatApi {
        @GET("messages")
        suspend fun getMessages(): List<Message>

        @POST("messages")
        suspend fun sendMessage(@Body message: Message): Message

        @DELETE("messages/{messageId}")
        suspend fun deleteMessage(@Path("messageId") messageId: String)

        @PUT("messages/{messageId}")
        suspend fun updateMessage(@Path("messageId") messageId: String, @Body message: Message)
    }

    companion object {
        fun create(retrofit: Retrofit): RemoteChatDataSource {
            val api = retrofit.create(ChatApi::class.java)
            return RemoteChatDataSource(api)
        }
    }
}
