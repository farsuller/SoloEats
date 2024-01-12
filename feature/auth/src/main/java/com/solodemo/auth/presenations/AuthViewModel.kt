package com.solodemo.auth.presenations

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.components.Constants.ACCESS_TOKEN
import com.solo.components.state.RequestState
import com.solo.util.SharedPreferenceHelper
import com.solodemo.supabase.domain.repository.SupabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composeAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: SupabaseRepository,
    application: Application
) : ViewModel() {

    val composeAuth = repository.supaBaseClient().composeAuth

    private val _uiState = MutableStateFlow<RequestState<*>>(RequestState.Loading)
    val uiState: StateFlow<RequestState<*>>
        get() = _uiState

    var loginButtonClicked = mutableStateOf(false)
        private set

    var googleButtonClicked = mutableStateOf(false)
        private set
    var loadingState = mutableStateOf(false)
        private set

    var composeAuthState = mutableStateOf(false)
        private set

    fun setLoginClicked(loading: Boolean) {
        loginButtonClicked.value = loading
    }

    fun setGoogleClicked(loading: Boolean) {
        googleButtonClicked.value = loading
    }

    fun setLoading(loading: Boolean) {
        loadingState.value = loading
    }

    fun setComposeAuthLoading(loading: Boolean) {
        composeAuthState.value = loading
    }

    private val sharedPref = SharedPreferenceHelper(application)

    fun signInEmail(email: String, password: String) {
        viewModelScope.launch {
            repository.signInEmail(authEmail = email, authPassword = password)
                .collectLatest { data ->
                    when (data) {
                        RequestState.Loading -> {
                            _uiState.value = RequestState.Loading
                        }

                        is RequestState.Success -> {
                            _uiState.value = RequestState.Success(data.data)
                            saveToken()
                        }

                        is RequestState.Error -> {
                            _uiState.value = RequestState.Error(data.error)
                        }

                        else -> {}
                    }
                }
        }
    }

    fun signUpEmail(email: String, password: String) {
        viewModelScope.launch {
            repository.signUpEmail(authEmail = email, authPassword = password)
                .collectLatest { data ->
                    when (data) {
                        RequestState.Loading -> {
                            _uiState.value = RequestState.Loading
                        }

                        is RequestState.Success -> {
                            _uiState.value = RequestState.Success(data)
                        }

                        is RequestState.Error -> {
                            _uiState.value = RequestState.Error(data.error)
                        }

                        else -> {}
                    }
                }
        }
    }

    private fun saveToken() {
        viewModelScope.launch {
            repository.getCurrentAccessToken().collectLatest { token ->
                when (token) {
                    RequestState.Loading -> {
                        _uiState.value = RequestState.Loading
                    }

                    is RequestState.Success -> {
                        _uiState.value = RequestState.Success(token)
                        sharedPref.saveStringData(ACCESS_TOKEN, token.data)
                    }

                    is RequestState.Error -> {
                        _uiState.value = RequestState.Error(token.error)
                    }

                    else -> {}
                }
            }

        }

    }

    private fun getToken(): String? {
        return sharedPref.getStringData(ACCESS_TOKEN)
    }

    fun isUserLoggedIn() {
        viewModelScope.launch {

            val token = getToken()
            if (token.isNullOrEmpty()) _uiState.value = RequestState.Error(Exception("User not logged In"))
            else {
                repository.refreshAccessToken(token).collectLatest { session ->
                    when (session) {
                        RequestState.Loading -> _uiState.value = RequestState.Loading
                        is RequestState.Success -> {
                            _uiState.value = RequestState.Success(token)
                            saveToken()
                        }
                        is RequestState.Error -> _uiState.value = RequestState.Error(session.error)
                        else -> {}
                    }
                }
            }

        }
    }

    fun checkGoogleLoginStatus(result: NativeSignInResult) {
        _uiState.value = RequestState.Loading
        when (result) {
            is NativeSignInResult.Success -> {
                saveToken()
                _uiState.value = RequestState.Success(result)
            }

            is NativeSignInResult.ClosedByUser -> {}
            is NativeSignInResult.Error -> {
                val message = result.message
                _uiState.value = RequestState.Error(Exception(message))

            }

            is NativeSignInResult.NetworkError -> {
                val message = result.message
                _uiState.value = RequestState.Error(Exception(message))
            }
        }
    }
}
