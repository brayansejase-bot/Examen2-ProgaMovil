package org.ucb.appp1.tasks.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.appp1.tasks.domain.repository.TaskRepository

class ObserveCompletedCountUseCase(private val repository: TaskRepository) {
    operator fun invoke(): Flow<Int> = repository.getCompletedCount()
}
