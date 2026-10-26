package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.tasks.data.datasource.TaskFirestoreDataSource
import org.ucb.appp1.tasks.data.datasource.TaskRealtimeDataSource
import org.ucb.appp1.tasks.data.repository.TaskRepositoryImpl
import org.ucb.appp1.tasks.domain.repository.TaskRepository

val dataModule = module {
    singleOf(::TaskFirestoreDataSource)
    singleOf(::TaskRealtimeDataSource)
    single<TaskRepository> { TaskRepositoryImpl(get(), get()) }
}
