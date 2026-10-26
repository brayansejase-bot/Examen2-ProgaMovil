package org.ucb.appp1.tasks.domain

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String = "",
    val title: String = "",
    val completed: Boolean = false,
    val timestamp: Long = 0L
)
