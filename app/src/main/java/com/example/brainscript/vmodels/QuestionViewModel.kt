package com.example.brainscript.vmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainscript.model.Question
import com.example.brainscript.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    fun loadQuestionsByCategory(categoryId: Int) = viewModelScope.launch {
        _questions.value = repository.getQuestionsByCategory(categoryId)
    }

    fun insert(question: Question) = viewModelScope.launch {
        repository.insert(question)
        loadQuestionsByCategory(question.categoryId)
    }

    fun update(question: Question) = viewModelScope.launch {
        repository.update(question)
        loadQuestionsByCategory(question.categoryId)
    }

    fun delete(question: Question) = viewModelScope.launch {
        repository.delete(question)
        loadQuestionsByCategory(question.categoryId)
    }
}
