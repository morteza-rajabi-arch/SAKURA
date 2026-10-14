
package com.morteza.sakura

import android.app.Application
import androidx.room.Room
import com.morteza.sakura.data.local.SakuraDatabase
import com.morteza.sakura.data.repository.GoalRepository
import com.morteza.sakura.data.repository.ProjectRepository
import com.morteza.sakura.data.repository.StudySessionRepository
import com.morteza.sakura.data.repository.TaskRepository

class SakuraApp : Application() {

    val database: SakuraDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            SakuraDatabase::class.java,
            "sakura_database"
        ).build()
    }

    val taskRepository: TaskRepository by lazy {
        TaskRepository(database.taskDao())
    }

    val goalRepository: GoalRepository by lazy {
        GoalRepository(database.goalDao())
    }

    val projectRepository: ProjectRepository by lazy {
        ProjectRepository(database.projectDao())
    }

    val studySessionRepository: StudySessionRepository by lazy {
        StudySessionRepository(database.studySessionDao())
    }
}
