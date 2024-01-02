package com.solodemo.main


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodemo.supabase.model.RequestState
import com.solodemo.supabase.repository.Menus
import com.solodemo.supabase.repository.MenusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MenusRepository
) : ViewModel() {

    var menus : MutableState<Menus> = mutableStateOf(RequestState.Idle)

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
}