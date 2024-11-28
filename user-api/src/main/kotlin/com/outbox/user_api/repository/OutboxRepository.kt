package com.outbox.user_api.repository

import com.outbox.user_api.domain.entity.Outbox
import com.outbox.user_api.domain.enums.Status
import org.springframework.data.jpa.repository.JpaRepository

interface OutboxRepository : JpaRepository<Outbox, Long> {

    fun findAllByStatusIn(status: List<Status>) : List<Outbox>
    fun findByUserId(userId: Long) : Outbox
}