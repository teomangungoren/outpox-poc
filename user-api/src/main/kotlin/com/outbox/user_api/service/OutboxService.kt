package com.outbox.user_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.outbox.user_api.domain.entity.Outbox
import com.outbox.user_api.domain.entity.User
import com.outbox.user_api.repository.OutboxRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OutboxService(
    private val outboxRepository: OutboxRepository,
    private val objectMapper: ObjectMapper,
    private val notifyService: NotifyService
) {

    @Transactional
    fun save(user: User) {
        val payload = objectMapper.writeValueAsString(user)
        val outbox = Outbox(payload = payload)
        val savedOutbox = outboxRepository.save(outbox)
        notifyService.notify(objectMapper.writeValueAsString(savedOutbox))
    }
}