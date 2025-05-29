package com.example.brainscript.repository
import com.example.brainscript.model.Answer

interface AnswerRepository : BaseRepository<Answer>{
    override suspend fun insert(entity: Answer)
    override suspend fun update(entity: Answer)
    override suspend fun delete(entity: Answer)
    suspend fun getAnswersForUser(userId: Int): List<Answer>
    suspend fun getCorrectAnswersForQuestion(questionId: Int): List<Answer>
}