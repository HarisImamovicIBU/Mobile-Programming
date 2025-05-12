package com.example.brainscript.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.brainscript.dao.UserDao
import com.example.brainscript.dao.CategoryDao
import com.example.brainscript.dao.QuestionDao
import com.example.brainscript.dao.AnswerDao
import com.example.brainscript.model.Users
import com.example.brainscript.model.Category
import com.example.brainscript.model.Question
import com.example.brainscript.model.Answer

@Database(
    entities = [Users::class, Category::class, Question::class, Answer::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun categoryDao(): CategoryDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
}