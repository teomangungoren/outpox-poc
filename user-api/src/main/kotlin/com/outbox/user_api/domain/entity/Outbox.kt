package com.outbox.user_api.domain.entity

import com.outbox.user_api.domain.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Outbox(
    @Id
    @GeneratedValue
    val id: Long? = null,
    @Enumerated(EnumType.STRING)
    val status: Status? = Status.PENDING,
    @Column(length = 200)
    val payload: String
)