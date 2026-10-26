package org.ucb.appp1.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.ucb.appp1.tasks.data.service.AndroidNotificationService
import org.ucb.appp1.tasks.domain.repository.NotificationService

actual fun platformModule(): Module = module {
    single<NotificationService> { AndroidNotificationService(get()) }
}
