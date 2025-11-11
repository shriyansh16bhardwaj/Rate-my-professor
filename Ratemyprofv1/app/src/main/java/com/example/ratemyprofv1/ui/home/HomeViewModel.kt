package com.example.ratemyprofv1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemyprofv1.data.Repository
import com.example.ratemyprofv1.data.model.Professor
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _professors = MutableLiveData<List<Professor>>(emptyList())
    val professors: LiveData<List<Professor>> = _professors

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadProfessors(query: String? = null) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getProfessors(query)
                if (response.isSuccessful) {
                    _professors.value = response.body().orEmpty()
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


