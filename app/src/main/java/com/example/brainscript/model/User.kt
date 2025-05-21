package com.example.brainscript.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Serializable
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val password: String,
    val totalScore: Int
)
