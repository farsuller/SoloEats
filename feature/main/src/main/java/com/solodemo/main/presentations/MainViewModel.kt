package com.solodemo.main.presentations


import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.solo.components.Constants
import com.solo.components.state.RequestState
import com.solo.util.SharedPreferenceHelper
import com.solo.util.getJsonDataFromAsset
import com.solodemo.main.model.FoodCategory
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reels
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

    var selectedCategory: String? = null
    var menus : MutableState<Menus> = mutableStateOf(RequestState.Idle)
    var reels : MutableState<Reels> = mutableStateOf(RequestState.Idle)
    private val sharedPref = SharedPreferenceHelper(application.applicationContext)
    init {
        getReels()
        getMenus()
    }
    fun getProductList(context: Context): List<FoodCategory> {
        val jsonFileString = getJsonDataFromAsset(context, "foodProducts.json")
        val type = object : TypeToken<List<FoodCategory>>() {}.type
        return Gson().fromJson(jsonFileString, type)
    }

    private fun getReels(){
        viewModelScope.launch {
            repository.getReels().collectLatest { data ->
                reels.value = data

                Log.d("MainViewModel","reels are $data")
            }
        }
    }

    private fun getMenus(){
        viewModelScope.launch {
            repository.getMenus().collectLatest { data ->
                menus.value = data
                Log.d("MainViewModel","menus are $data")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut().collectLatest { data ->
                _uiState.update { data }
                sharedPref.clearPreferences()
            }
        }
    }
}