package org.ucb.appp1.tasks.domain.repository

interface NotificationService {
    fun showNotification(title: String, message: String)
}
