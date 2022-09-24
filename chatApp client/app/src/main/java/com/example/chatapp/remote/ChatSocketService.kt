package com.example.chatapp.remote

import com.example.chatapp.domain.model.Message
import com.example.chatapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        username: String
    ): Resource<Unit>


    suspend fun sendMessage(message: String)

    fun observeMessage(): Flow<Message>

    suspend fun closeSession()

    companion object{
        const val BASE_URL="http://10.0.2.2.8080"
    }

    sealed class Endpoints(val url : String){
        object ChatSocket : Endpoints("$BASE_URL/chat_socket")
    }
}