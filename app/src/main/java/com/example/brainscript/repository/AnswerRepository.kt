package com.example.brainscript.repository
import com.example.brainscript.model.Answer

interface AnswerRepository : BaseRepository<Answer>{
    suspend fun getAnswersForUser(userId: Int): List<Answer>
    suspend fun getCorrectAnswersForQuestion(questionId: Int): List<Answer>
}