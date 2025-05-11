package com.example.brainscript.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.brainscript.model.Question

@Dao
interface QuestionDao : BaseDao<Question> {
    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestionById(id: Int): Question?

    @Query("SELECT * FROM questions WHERE categoryId = :categoryId")
    suspend fun getQuestionsByCategory(categoryId: Int): List<Question>
}