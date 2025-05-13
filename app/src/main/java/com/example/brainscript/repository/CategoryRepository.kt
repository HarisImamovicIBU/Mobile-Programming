package com.example.brainscript.repository
import com.example.brainscript.model.Category

interface CategoryRepository : BaseRepository<Category> {
    suspend fun getAllCategories(): List<Category>
    suspend fun getCategoryByName(categoryName: String): Category?
}