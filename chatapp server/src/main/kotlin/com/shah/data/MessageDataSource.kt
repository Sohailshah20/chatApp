package com.shah.data

import com.shah.data.model.Message

interface MessageDataSource {

    suspend fun getAllMessages() : List<Message>

    suspend fun insertMessage(message: Message)
}