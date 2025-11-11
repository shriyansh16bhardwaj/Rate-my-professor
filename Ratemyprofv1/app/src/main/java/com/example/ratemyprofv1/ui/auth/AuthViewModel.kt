package com.example.ratemyprofv1.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemyprofv1.data.Repository
import com.example.ratemyprofv1.data.SessionManager
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: Repository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    fun login(username: String, password: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)
                val body = response.body()
                if (response.isSuccessful && body?.success == true && body.token != null) {
                    sessionManager.saveToken(body.token)
                    _success.value = true
                } else {
                    _error.value = body?.message ?: "Login failed"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}


