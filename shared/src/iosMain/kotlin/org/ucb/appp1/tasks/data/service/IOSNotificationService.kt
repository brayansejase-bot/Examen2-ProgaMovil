package org.ucb.appp1.tasks.data.service

import org.ucb.appp1.tasks.domain.repository.NotificationService

class IOSNotificationService : NotificationService {
    override fun showNotification(title: String, message: String) {
        // No-op for iOS
    }
}
