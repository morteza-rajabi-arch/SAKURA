
package com.morteza.sakura.domain.model

data class Task(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val status: TaskStatus = TaskStatus.TODO,
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val dueDate: Long? = null,
    val estimatedMinutes: Int? = null,
    val actualMinutes: Int = 0,
    val isRecurring: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
