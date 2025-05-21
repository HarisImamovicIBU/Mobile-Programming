package com.example.brainscript.vmodels
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainscript.model.User
import com.example.brainscript.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _registrationSuccess = MutableStateFlow(false)
    val registrationSuccess: StateFlow<Boolean> = _registrationSuccess

    private val _loginStatus = MutableStateFlow<Boolean?>(null)
    val loginStatus: StateFlow<Boolean?> = _loginStatus

    private val _loggedUser = MutableStateFlow<User?>(null)
    val loggedUser: StateFlow<User?> = _loggedUser

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun register(email: String, password: String, firstName: String, lastName: String, phoneNumber: String, totalScore: Int) {
        viewModelScope.launch {
            try {
                repository.insert(User(email=email, password=password, firstName=firstName, lastName=lastName, phoneNumber=phoneNumber, totalScore=0))
                _registrationSuccess.value = true
            } catch (e: Exception) {
                _error.value = "Registration failed: ${e.message}"
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.
            getUserByEmailAndPassword(email = email, password = password);
            _loginStatus.value = user != null;
            if (_loginStatus.value == true) {
                _loggedUser.value = user
                Log.d("logged", "_loggedUser ${loggedUser.value}")
            }
        }
    }

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
