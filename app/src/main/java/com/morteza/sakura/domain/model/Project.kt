
package com.morteza.sakura.domain.model

data class Project(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val startDate: Long? = null,
    val deadline: Long? = null,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
