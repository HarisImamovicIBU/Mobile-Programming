package com.example.brainscript.vmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainscript.model.User
import com.example.brainscript.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    fun loadAllUsers() = viewModelScope.launch {
        _users.value = repository.getAllUsers()
    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
        loadAllUsers()
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
        loadAllUsers()
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
        loadAllUsers()
    }

    suspend fun getUserByEmail(email: String): User? {
        return repository.getUserByEmail(email)
    }

    suspend fun getUserByFirstname(firstName: String): User? {
        return repository.getUserByFirstname(firstName)
    }

    suspend fun getUserByLastname(lastName: String): User? {
        return repository.getUserByLastname(lastName)
    }
}
