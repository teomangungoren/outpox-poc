package com.outbox.user_api.listener

import com.fasterxml.jackson.databind.ObjectMapper
import com.outbox.user_api.domain.enums.Status
import com.outbox.user_api.domain.request.NotifyMessage
import com.outbox.user_api.service.OutboxService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class NotificationListener(private val objectMapper: ObjectMapper, private val outboxService: OutboxService) {


    @KafkaListener(topics = ["\${spring.kafka.topic.outbox-processed}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun listen(@Payload msg: String) {
        val notifyMessage = objectMapper.readValue(msg, NotifyMessage::class.java)
        val userOutbox = outboxService.findByUserId(notifyMessage.userId)
        val status = Status.valueOf(notifyMessage.status)
        userOutbox.status = status
        outboxService.update(userOutbox)
    }
}