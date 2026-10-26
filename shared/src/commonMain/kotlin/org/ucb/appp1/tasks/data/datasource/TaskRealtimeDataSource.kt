package org.ucb.appp1.tasks.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TaskRealtimeDataSource {
    private val database by lazy { Firebase.database }
    private val counterRef by lazy { database.reference("stats/completadas") }

    fun observeCompletedCount(): Flow<Int> {
        return counterRef.valueEvents.map { snapshot ->
            snapshot.value<Int?>() ?: snapshot.value<Long?>()?.toInt() ?: 0
        }
    }

    suspend fun incrementCounter(delta: Int) {
        val snapshot = counterRef.valueEvents.first()
        val current = snapshot.value<Int?>() ?: snapshot.value<Long?>()?.toInt() ?: 0
        val newCount = (current + delta).coerceAtLeast(0)
        counterRef.setValue(newCount)
    }
}
