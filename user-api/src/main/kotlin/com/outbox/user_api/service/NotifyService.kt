package com.outbox.user_api.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class NotifyService(private val kafkaTemplate: KafkaTemplate<String,Any>) {

    @Value("\${spring.kafka.topic.user-created}")
    private lateinit var userCreatedTopic: String


    fun notify(message: String) {
        println(userCreatedTopic)
        println(message)
        kafkaTemplate.send(userCreatedTopic,message)
    }
}