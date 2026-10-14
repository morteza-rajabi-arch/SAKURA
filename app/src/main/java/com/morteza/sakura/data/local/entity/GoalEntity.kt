
package com.morteza.sakura.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val targetDate: Long? = null,
    val progressPercent: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
