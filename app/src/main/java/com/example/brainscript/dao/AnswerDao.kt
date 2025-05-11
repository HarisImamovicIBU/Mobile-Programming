package com.example.brainscript.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.brainscript.model.Answer

@Dao
interface AnswerDao : BaseDao<Answer> {
    @Query("SELECT * FROM answers WHERE userId = :userId")
    suspend fun getAnswersByUserId(userId: Int): List<Answer>

    @Query("SELECT * FROM answers WHERE questionId = :questionId")
    suspend fun getAnswersByQuestionId(questionId: Int): List<Answer>

    @Query("SELECT * FROM answers WHERE userId = :userId AND questionId = :questionId")
    suspend fun getAnswerForUserAndQuestion(userId: Int, questionId: Int): Answer?
}