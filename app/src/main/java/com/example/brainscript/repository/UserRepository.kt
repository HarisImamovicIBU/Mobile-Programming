package com.example.brainscript.repository
import com.example.brainscript.model.User

interface UserRepository : BaseRepository<User> {
    suspend fun getUserByEmail(email: String): User?
    suspend fun getUserByFirstname(firstName: String) : User?
    suspend fun getUserByLastname(lastName: String) : User?
    suspend fun getAllUsers(): List<User>
}
