package com.example.ratemyprofv1.ui.professor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemyprofv1.data.Repository
import com.example.ratemyprofv1.data.model.Professor
import kotlinx.coroutines.launch

class ProfessorViewModel(private val repository: Repository) : ViewModel() {
    private val _professor = MutableLiveData<Professor?>()
    val professor: LiveData<Professor?> = _professor

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadProfessor(id: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getProfessorDetails(id)
                if (response.isSuccessful) {
                    _professor.value = response.body()
                } else {
                    _error.value = "Failed to load"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}


