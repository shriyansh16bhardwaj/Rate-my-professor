package com.example.ratemyprofv1.ui.myreviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemyprofv1.data.Repository
import com.example.ratemyprofv1.data.model.Review
import kotlinx.coroutines.launch

class MyReviewsViewModel(private val repository: Repository) : ViewModel() {
    private val _reviews = MutableLiveData<List<Review>>(emptyList())
    val reviews: LiveData<List<Review>> = _reviews

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadMyReviews(userId: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getMyReviews(userId)
                if (response.isSuccessful) {
                    _reviews.value = response.body().orEmpty()
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


