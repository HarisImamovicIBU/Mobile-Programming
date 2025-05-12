package com.example.brainscript.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.brainscript.model.Users

@Dao
interface UserDao : BaseDao<Users> {
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: Int): Users?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): Users?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): Users?

    @Query("UPDATE users SET totalScore = :score WHERE id = :id")
    suspend fun updateTotalScore(id: Int, score: Int)
}