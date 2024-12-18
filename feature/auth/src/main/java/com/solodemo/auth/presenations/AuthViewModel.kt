package com.solodemo.auth.presenations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.solo.components.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit,
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (email.isEmpty() || password.isEmpty()) {
            onError(Exception("Email or password can't be empty."))
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                    _authState.value = AuthState.Authenticated
                } else {
                    onError(Exception(task.exception?.message ?: "Something went wrong"))
                }
            }
    }

    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit,
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (email.isEmpty() || password.isEmpty()) {
            onError(Exception("Email or password can't be empty."))
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                    onSuccess()
                } else {
                    onError(Exception(task.exception?.message ?: "Something went wrong"))
                }
            }
    }
}
