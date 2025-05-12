package com.example.brainscript.di

import android.content.Context
import androidx.room.Room
import com.example.brainscript.dao.*
import com.example.brainscript.database.AppDatabase
import com.example.brainscript.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "brain_script.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao = appDatabase.categoryDao()

    @Provides
    fun provideQuestionDao(appDatabase: AppDatabase): QuestionDao = appDatabase.questionDao()

    @Provides
    fun provideAnswerDao(appDatabase: AppDatabase): AnswerDao = appDatabase.answerDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryDao: CategoryDao): CategoryRepository = CategoryRepositoryImpl(categoryDao)

    @Provides
    @Singleton
    fun provideQuestionRepository(questionDao: QuestionDao): QuestionRepository = QuestionRepositoryImpl(questionDao)

    @Provides
    @Singleton
    fun provideAnswerRepository(answerDao: AnswerDao): AnswerRepository = AnswerRepositoryImpl(answerDao)
}
