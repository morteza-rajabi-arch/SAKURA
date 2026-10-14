
package com.morteza.sakura.domain.model

data class StudySession(
    val id: Long = 0,
    val title: String,
    val subject: String,
    val scheduledStart: Long,
    val scheduledEnd: Long,
    val actualMinutes: Int = 0,
    val isCompleted: Boolean = false,
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
