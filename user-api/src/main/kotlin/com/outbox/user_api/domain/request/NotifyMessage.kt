package com.outbox.user_api.domain.request

data class NotifyMessage(
    val userId: Long,
    val status: String
)
