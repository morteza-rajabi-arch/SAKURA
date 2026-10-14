
package com.morteza.sakura.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morteza.sakura.data.local.entity.GoalEntity
import com.morteza.sakura.data.local.entity.ProjectEntity
import com.morteza.sakura.data.local.entity.StudySessionEntity
import com.morteza.sakura.data.local.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class,
        GoalEntity::class,
        ProjectEntity::class,
        StudySessionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class SakuraDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun goalDao(): GoalDao

    abstract fun projectDao(): ProjectDao

    abstract fun studySessionDao(): StudySessionDao
}
