package com.example.brainscript.repository
import com.example.brainscript.model.Question

interface QuestionRepository : BaseRepository<Question> {
    suspend fun getQuestionsByCategory(categoryId: Int): List<Question>
}
