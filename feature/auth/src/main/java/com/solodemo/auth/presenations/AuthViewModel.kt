package com.solodemo.auth.presenations

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.components.state.RequestState
import com.solo.util.Constants.ACCESS_TOKEN
import com.solo.util.SharedPreferenceHelper
import com.solodemo.supabase.domain.repository.SupabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.exceptions.RestException
import io.ktor.util.Identity.decode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: SupabaseRepository,
    application: Application
) : ViewModel() {


    private val _uiState = MutableStateFlow<RequestState<*>>(RequestState.Loading)
    val uiState: StateFlow<RequestState<*>>
        get() = _uiState


    private val sharedPref = SharedPreferenceHelper(application)

    fun signInEmail(email: String, password: String) {
        viewModelScope.launch {
                repository.signInEmail(authEmail = email, authPassword = password)
                    .collectLatest { data ->
                        _uiState.update { data }
                        saveToken()
                    }
        }
    }

    private fun saveToken() {
        viewModelScope.launch {
            repository.getCurrentAccessToken().collectLatest { token ->
                when(token){
                    RequestState.Loading -> {
                        _uiState.value = RequestState.Loading
                    }
                    is RequestState.Success -> {
                        _uiState.value = RequestState.Success(token)
                        sharedPref.saveStringData(ACCESS_TOKEN,token.data)
                    }
                    is RequestState.Error -> {
                        _uiState.value = RequestState.Error(token.error)
                    }
                    else ->{}
                }
            }

        }

    }

    private fun getToken(): String? {
        return sharedPref.getStringData(ACCESS_TOKEN)
    }

    fun isUserLoggedIn(callback: (Boolean) -> Unit){
        viewModelScope.launch {

            val token = getToken()
            if(token.isNullOrEmpty()){
                _uiState.value = RequestState.Success("User not logged in!")
                callback(false)
            }
            else {
                repository.refreshAccessToken(token).collectLatest { session ->
                    when(session){
                        RequestState.Loading -> {
                            _uiState.value = RequestState.Loading
                        }
                        is RequestState.Success -> {
                            _uiState.value = RequestState.Success(token)
                            saveToken()
                            callback(true)
                        }
                        is RequestState.Error -> {
                            _uiState.value = RequestState.Error(session.error)
                            callback(false)
                        }
                        else ->{
                            callback(false)
                        }
                    }
                }
            }

        }
    }
}
