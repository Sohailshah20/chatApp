package com.example.chatapp.di

import com.example.chatapp.remote.ChatSocketService
import com.example.chatapp.remote.ChatSocketServiceImpl
import com.example.chatapp.remote.MessageService
import com.example.chatapp.remote.MessageServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesHttpClient() : HttpClient{
        return HttpClient(CIO){
            install(Logging)
            install(WebSockets)
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }

        }
    }

    @Provides
    @Singleton
    fun providesMessageService(client : HttpClient) : MessageService {
        return MessageServiceImpl(client)
    }

    @Provides
    @Singleton
    fun providesChatSocketService(client : HttpClient) : ChatSocketService {
        return ChatSocketServiceImpl(client)
    }
}