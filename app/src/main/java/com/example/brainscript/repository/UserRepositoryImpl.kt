package com.example.brainscript.repository
import com.example.brainscript.dao.UserDao
import com.example.brainscript.model.User

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {
    override suspend fun insert(entity: User) = dao.insert(entity)
    override suspend fun update(entity: User) = dao.update(entity)
    override suspend fun delete(entity: User) = dao.delete(entity)

    override suspend fun getUserByEmail(email: String): User? = dao.getUserByEmail(email)
    override suspend fun getAllUsers(): List<User> = dao.getAllUsers()
    override suspend fun getUserByFirstname(firstName: String) : User? = dao.getUserByFirstname(firstName)
    override suspend fun getUserByLastname(lastName: String) : User? = dao.getUserByLastname(lastName)
    override suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return dao.getUserByEmailAndPassword(email, password)
    }
}