package com.outbox.user_api.repository

import com.outbox.user_api.domain.entity.Outbox
import org.springframework.data.jpa.repository.JpaRepository

interface OutboxRepository : JpaRepository<Outbox, Long> {
}