
package com.morteza.sakura.domain.model

data class Goal(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val targetDate: Long? = null,
    val progressPercent: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

