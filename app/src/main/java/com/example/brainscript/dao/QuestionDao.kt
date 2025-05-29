package com.example.brainscript.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.brainscript.model.Question

@Dao
interface QuestionDao : BaseDao<Question> {
    @Insert
    override suspend fun insert(entity: Question)

    @Update
    override suspend fun update(entity: Question)

    @Delete
    override suspend fun delete(entity: Question)

    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestionById(id: Int): Question?

    @Query("SELECT * FROM questions WHERE categoryId = :categoryId")
    suspend fun getQuestionsByCategory(categoryId: Int): List<Question>
}