
package com.morteza.sakura.presentation.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.morteza.sakura.SakuraApp
import com.morteza.sakura.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        (application as SakuraApp).taskRepository

    val tasks = repository.getAllTasks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addTask(
        title: String,
        description: String = "",
        priority: String = "MEDIUM"
    ) {
        val cleanTitle = title.trim()
        if (cleanTitle.isBlank()) return

        viewModelScope.launch {
            repository.insertTask(
                TaskEntity(
                    title = cleanTitle,
                    description = description.trim(),
                    priority = priority
                )
            )
        }
    }

    fun toggleTaskStatus(task: TaskEntity) {
        val newStatus = when (task.status) {
            "COMPLETED" -> "TODO"
            else -> "COMPLETED"
        }

        viewModelScope.launch {
            repository.updateTask(
                task.copy(status = newStatus)
            )
        }
    }

    fun setTaskInProgress(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(
                task.copy(status = "IN_PROGRESS")
            )
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}
