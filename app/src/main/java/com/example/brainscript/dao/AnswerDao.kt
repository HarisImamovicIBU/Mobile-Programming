package com.example.brainscript.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.brainscript.model.Answer

@Dao
interface AnswerDao : BaseDao<Answer>{
    @Insert
    override suspend fun insert(entity: Answer)

    @Update
    override suspend fun update(entity: Answer)

    @Delete
    override suspend fun delete(entity: Answer)

    @Query("SELECT * FROM answers WHERE userId = :userId")
    suspend fun getAnswersByUserId(userId: Int): List<Answer>

    @Query("SELECT * FROM answers WHERE questionId = :questionId")
    suspend fun getAnswersByQuestionId(questionId: Int): List<Answer>

    @Query("SELECT * FROM answers WHERE userId = :userId AND questionId = :questionId")
    suspend fun getAnswerForUserAndQuestion(userId: Int, questionId: Int): Answer?
}