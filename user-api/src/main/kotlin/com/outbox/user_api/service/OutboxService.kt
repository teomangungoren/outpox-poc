package com.outbox.user_api.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.outbox.user_api.domain.entity.Outbox
import com.outbox.user_api.domain.entity.User
import com.outbox.user_api.domain.enums.Status
import com.outbox.user_api.repository.OutboxRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OutboxService(
    private val outboxRepository: OutboxRepository,
    private val objectMapper: ObjectMapper,
) {

    @Transactional
    fun save(user: User) {
        val payload = objectMapper.writeValueAsString(user)
        val outbox = Outbox(userId = user.id!!, payload = payload)
        outboxRepository.save(outbox)
    }


    fun getListToSendMessage(statuses: List<Status>) = outboxRepository.findAllByStatusIn(statuses)


    fun findByUserId(userId: Long) = outboxRepository.findByUserId(userId)

    fun update(outbox: Outbox) = outboxRepository.save(outbox)

    fun deleteAll(listOutbox: List<Outbox>) = outboxRepository.deleteAll(listOutbox)


}