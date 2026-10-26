package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.tasks.domain.usecase.AddTaskUseCase
import org.ucb.appp1.tasks.domain.usecase.DeleteTaskUseCase
import org.ucb.appp1.tasks.domain.usecase.ObserveCompletedCountUseCase
import org.ucb.appp1.tasks.domain.usecase.ObserveTasksUseCase
import org.ucb.appp1.tasks.domain.usecase.ToggleTaskUseCase

val domainModule = module {
    singleOf(::ObserveTasksUseCase)
    singleOf(::ObserveCompletedCountUseCase)
    singleOf(::ToggleTaskUseCase)
    singleOf(::AddTaskUseCase)
    singleOf(::DeleteTaskUseCase)
}
