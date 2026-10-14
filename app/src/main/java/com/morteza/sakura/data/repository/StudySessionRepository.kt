
package com.morteza.sakura.data.repository

import com.morteza.sakura.data.local.StudySessionDao
import com.morteza.sakura.data.local.entity.StudySessionEntity
import kotlinx.coroutines.flow.Flow


class StudySessionRepository(
    private val studySessionDao: StudySessionDao
) {
    fun getAllSessions(): Flow<List<StudySessionEntity>> {
        return studySessionDao.getAllSessions()
    }


    fun getSessionById(
        sessionId: Long
    ): Flow<StudySessionEntity?> {
        return studySessionDao.observeSessionById(sessionId)
    }


    fun getSessionsBetween(
        startTime: Long,
        endTime: Long
    ): Flow<List<StudySessionEntity>> {
        return studySessionDao.getSessionsBetween(
            startTime,
            endTime
        )
    }

    suspend fun insertSession(
        session: StudySessionEntity
    ): Long {
        return studySessionDao.insertSession(session)
    }

    suspend fun updateSession(
        session: StudySessionEntity
    ) {
        studySessionDao.updateSession(session)
    }

    suspend fun deleteSession(
        session: StudySessionEntity
    ) {
        studySessionDao.deleteSession(session)
    }
}
