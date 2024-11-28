package com.outbox.user_api.scheduler

import com.fasterxml.jackson.databind.ObjectMapper
import com.outbox.user_api.domain.enums.Status
import com.outbox.user_api.service.NotifyService
import com.outbox.user_api.service.OutboxService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class OutboxScheduler(
    private val notifyService: NotifyService,
    private val outboxService: OutboxService,
    private val objectMapper: ObjectMapper
) {


    @Scheduled(fixedDelay = 50000)
    fun processOutboxMessage() {
        val statuses = listOf(Status.FAILED, Status.PENDING, Status.SUCCESS)
        val messages = outboxService.getListToSendMessage(statuses)
        val messagesToDelete = messages.filter { it.status == Status.SUCCESS }
        val messagesToSend = messages.filter { it.status in listOf(Status.FAILED, Status.PENDING) }
        messagesToSend.forEach {
            notifyService.notify(objectMapper.writeValueAsString(it))
            it.status = Status.PROCESSED
            outboxService.update(it)

        }
        if (messagesToDelete.isNotEmpty()) outboxService.deleteAll(messagesToDelete)
    }
}