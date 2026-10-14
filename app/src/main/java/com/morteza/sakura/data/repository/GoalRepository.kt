
package com.morteza.sakura.data.repository

import com.morteza.sakura.data.local.GoalDao
import com.morteza.sakura.data.local.entity.GoalEntity
import kotlinx.coroutines.flow.Flow

class GoalRepository(
    private val goalDao: GoalDao
) {
    fun getAllGoals(): Flow<List<GoalEntity>> {
        return goalDao.getAllGoals()
    }

    suspend fun getGoalById(goalId: Long): GoalEntity? {
        return goalDao.getGoalById(goalId)
    }

    suspend fun insertGoal(goal: GoalEntity): Long {
        return goalDao.insertGoal(goal)
    }

    suspend fun updateGoal(goal: GoalEntity) {
        goalDao.updateGoal(goal)
    }

    suspend fun deleteGoal(goal: GoalEntity) {
        goalDao.deleteGoal(goal)
    }
}
