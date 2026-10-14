
package com.morteza.sakura.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.morteza.sakura.data.local.entity.StudySessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudySessionDao {

    @Query("SELECT * FROM study_sessions ORDER BY scheduledStart ASC")
    fun getAllSessions(): Flow<List<StudySessionEntity>>


    @Query("SELECT * FROM study_sessions WHERE id = :sessionId LIMIT 1")
    fun observeSessionById(sessionId: Long): Flow<StudySessionEntity?>

    @Query("SELECT * FROM study_sessions WHERE id = :sessionId LIMIT 1")
    suspend fun getSessionById(sessionId: Long): StudySessionEntity?

    @Query(
        """
        SELECT * FROM study_sessions
        WHERE scheduledStart >= :startTime
        AND scheduledStart < :endTime
        ORDER BY scheduledStart ASC
        """
    )
    fun getSessionsBetween(
        startTime: Long,
        endTime: Long
    ): Flow<List<StudySessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: StudySessionEntity): Long

    @Update
    suspend fun updateSession(session: StudySessionEntity)

    @Delete
    suspend fun deleteSession(session: StudySessionEntity)
}
