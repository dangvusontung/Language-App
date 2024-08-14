package com.tungdvs.languageapp.di

import com.tungdvs.languageapp.data.model.api.chatcompletion.OpenAIService
import com.tungdvs.languageapp.domain.repositories.OpenAIRepository
import com.tungdvs.languageapp.data.repositories.OpenAIRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOpenAIService(): OpenAIService {
        return Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAIService::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenAIRepository(openAIService: OpenAIService): OpenAIRepository {
        return OpenAIRepositoryImpl(openAIService)
    }
}
