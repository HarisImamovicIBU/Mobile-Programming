package com.example.brainscript.repository
import com.example.brainscript.dao.CategoryDao
import com.example.brainscript.model.Category

class CategoryRepositoryImpl(private val dao: CategoryDao) : CategoryRepository {
    override suspend fun insert(entity: Category) = dao.insert(entity)
    override suspend fun update(entity: Category) = dao.update(entity)
    override suspend fun delete(entity: Category) = dao.delete(entity)

    override suspend fun getAllCategories(): List<Category> = dao.getAllCategories()
    override suspend fun getCategoryByName(categoryName: String): Category? = dao.getCategoryByName(categoryName)


}