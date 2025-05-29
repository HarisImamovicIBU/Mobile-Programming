package com.example.brainscript.vmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainscript.model.Answer
import com.example.brainscript.repository.AnswerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AnswerViewModel @Inject constructor(
    private val repository: AnswerRepository
) : ViewModel() {

    private val _userAnswers = MutableLiveData<List<Answer>>()
    val userAnswers: LiveData<List<Answer>> = _userAnswers

    private val _questionAnswers = MutableLiveData<List<Answer>>()
    val questionAnswers: LiveData<List<Answer>> = _questionAnswers

    fun insert(answer: Answer) = viewModelScope.launch {
        repository.insert(answer)
    }

    fun update(answer: Answer) = viewModelScope.launch {
        repository.update(answer)
    }

    fun delete(answer: Answer) = viewModelScope.launch {
        repository.delete(answer)
    }

    fun insertAnswer(answer: Answer) = viewModelScope.launch {
        repository.insert(answer)
    }

    fun loadAnswersForUser(userId: Int) = viewModelScope.launch {
        _userAnswers.value = repository.getAnswersForUser(userId)
    }

    fun loadAnswersForQuestion(questionId: Int) = viewModelScope.launch {
        _questionAnswers.value = repository.getCorrectAnswersForQuestion(questionId)
    }
}
