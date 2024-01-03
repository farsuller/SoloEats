package com.solodemo.main


import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.components.state.RequestState
import com.solo.util.SharedPreferenceHelper
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.SupabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SupabaseRepository,
    application: Application
) : ViewModel() {

    private val _uiState = MutableStateFlow<RequestState<*>>(RequestState.Loading)
    val uiState: StateFlow<RequestState<*>>
        get() = _uiState

    var menus : MutableState<Menus> = mutableStateOf(RequestState.Idle)
    private val sharedPref = SharedPreferenceHelper(application.applicationContext)
    init {
        getMenus()
    }

    private fun getMenus(){
        viewModelScope.launch {
            repository.getMenus().collectLatest { data ->
                menus.value = data
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            repository.singOut().collectLatest { data ->
                _uiState.update { data }
                sharedPref.clearPreferences()
            }
        }
    }
}