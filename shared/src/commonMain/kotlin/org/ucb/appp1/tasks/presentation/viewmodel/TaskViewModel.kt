package org.ucb.appp1.tasks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.ucb.appp1.tasks.domain.Task
import org.ucb.appp1.tasks.domain.repository.NotificationService
import org.ucb.appp1.tasks.domain.usecase.AddTaskUseCase
import org.ucb.appp1.tasks.domain.usecase.DeleteTaskUseCase
import org.ucb.appp1.tasks.domain.usecase.ObserveCompletedCountUseCase
import org.ucb.appp1.tasks.domain.usecase.ObserveTasksUseCase
import org.ucb.appp1.tasks.domain.usecase.ToggleTaskUseCase

class TaskViewModel(
    observeTasksUseCase: ObserveTasksUseCase,
    observeCompletedCountUseCase: ObserveCompletedCountUseCase,
    private val toggleTaskUseCase: ToggleTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val notificationService: NotificationService
) : ViewModel() {

    val tasks: StateFlow<List<Task>> = observeTasksUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val completedCount: StateFlow<Int> = observeCompletedCountUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    private val _newTaskTitle = MutableStateFlow("")
    val newTaskTitle: StateFlow<String> = _newTaskTitle.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun onTitleChanged(title: String) {
        _newTaskTitle.value = title
    }

    fun addTask() {
        val title = _newTaskTitle.value.trim()
        if (title.isEmpty()) return

        viewModelScope.launch {
            val result = addTaskUseCase(title)
            result.fold(
                onSuccess = {
                    _newTaskTitle.value = ""
                },
                onFailure = { error ->
                    _errorMessage.value = error.message
                }
            )
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            val result = toggleTaskUseCase(task)
            result.onFailure { error ->
                _errorMessage.value = error.message
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            val result = deleteTaskUseCase(task.id)
            result.onFailure { error ->
                _errorMessage.value = error.message
            }
        }
    }

    fun sendNotification() {
        notificationService.showNotification("Hola desde la app", "¡Esta es una notificación de tus tareas!")
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
