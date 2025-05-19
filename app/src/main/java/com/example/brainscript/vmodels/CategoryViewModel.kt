package com.example.brainscript.vmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brainscript.model.Category
import com.example.brainscript.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    fun loadAllCategories() = viewModelScope.launch {
        _categories.value = repository.getAllCategories()
    }

    fun insert(category: Category) = viewModelScope.launch {
        repository.insert(category)
        loadAllCategories()
    }

    fun update(category: Category) = viewModelScope.launch {
        repository.update(category)
        loadAllCategories()
    }

    fun delete(category: Category) = viewModelScope.launch {
        repository.delete(category)
        loadAllCategories()
    }

    suspend fun getCategoryByName(name: String): Category? {
        return repository.getCategoryByName(name)
    }
}
