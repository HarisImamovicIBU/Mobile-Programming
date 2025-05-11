package com.example.brainscript.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.brainscript.model.Category

@Dao
interface CategoryDao : BaseDao<Category> {
    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<Category>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategoryById(id: Int): Category?
}