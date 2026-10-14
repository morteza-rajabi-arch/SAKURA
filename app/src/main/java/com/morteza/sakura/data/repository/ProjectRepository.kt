
package com.morteza.sakura.data.repository

import com.morteza.sakura.data.local.ProjectDao
import com.morteza.sakura.data.local.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

class ProjectRepository(
    private val projectDao: ProjectDao
) {
    fun getAllProjects(): Flow<List<ProjectEntity>> {
        return projectDao.getAllProjects()
    }

    suspend fun getProjectById(projectId: Long): ProjectEntity? {
        return projectDao.getProjectById(projectId)
    }

    suspend fun insertProject(project: ProjectEntity): Long {
        return projectDao.insertProject(project)
    }

    suspend fun updateProject(project: ProjectEntity) {
        projectDao.updateProject(project)
    }

    suspend fun deleteProject(project: ProjectEntity) {
        projectDao.deleteProject(project)
    }
}

