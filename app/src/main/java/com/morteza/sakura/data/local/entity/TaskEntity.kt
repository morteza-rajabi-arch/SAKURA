
package com.morteza.sakura.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val status: String = "TODO",
    val priority: String = "MEDIUM",
    val dueDate: Long? = null,
    val estimatedMinutes: Int? = null,
    val actualMinutes: Int = 0,
    val isRecurring: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
