package com.example.brainscript.repository
import com.example.brainscript.dao.QuestionDao
import com.example.brainscript.model.Question

class QuestionRepositoryImpl(private val dao: QuestionDao) : QuestionRepository {
    override suspend fun insert(entity: Question) = dao.insert(entity)
    override suspend fun update(entity: Question) = dao.update(entity)
    override suspend fun delete(entity: Question) = dao.delete(entity)

    override suspend fun getQuestionsByCategory(categoryId: Int): List<Question> =
        dao.getQuestionsByCategory(categoryId)
}