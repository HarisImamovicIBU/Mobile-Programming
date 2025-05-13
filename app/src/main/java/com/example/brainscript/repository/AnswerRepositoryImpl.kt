package com.example.brainscript.repository
import com.example.brainscript.dao.AnswerDao
import com.example.brainscript.model.Answer

class AnswerRepositoryImpl(private val dao: AnswerDao) : AnswerRepository {
    override suspend fun insert(entity: Answer) = dao.insert(entity)
    override suspend fun update(entity: Answer) = dao.update(entity)
    override suspend fun delete(entity: Answer) = dao.delete(entity)

    override suspend fun getAnswersForUser(userId: Int): List<Answer> = dao.getAnswersByUserId(userId)
    override suspend fun getCorrectAnswersForQuestion(questionId: Int): List<Answer> = dao.getAnswersByQuestionId(questionId)
}