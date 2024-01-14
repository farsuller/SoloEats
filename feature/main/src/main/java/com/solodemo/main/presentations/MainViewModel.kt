package com.solodemo.main.presentations


import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.solo.components.Constants
import com.solo.components.state.RequestState
import com.solo.util.SharedPreferenceHelper
import com.solo.util.getJsonDataFromAsset
import com.solodemo.main.model.FoodCategory
import com.solodemo.supabase.domain.repository.Carts
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews
import com.solodemo.supabase.domain.repository.SupabaseRepository
import com.solodemo.supabase.model.Cart
import com.solodemo.supabase.model.UserDetails
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

    private val _cartState = MutableStateFlow<RequestState<*>>(RequestState.Loading)
    val cartState: StateFlow<RequestState<*>>
        get() = _cartState

    var menus: MutableState<Menus> = mutableStateOf(RequestState.Idle)
    var reviews: MutableState<Reviews> = mutableStateOf(RequestState.Idle)
    var carts: MutableState<Carts> = mutableStateOf(RequestState.Idle)
    var user: MutableState<RequestState<UserDetails>> = mutableStateOf(RequestState.Idle)

    private val sharedPref = SharedPreferenceHelper(application.applicationContext)

    init {
        getReviews()
        getMenus()
        getUserInfo()
        getCartList()
    }

    fun insertCart(cart: Cart){
        viewModelScope.launch {
            repository.insertCart(cart = cart).collectLatest { data ->
                _cartState.update { data }
            }
        }
    }

    fun getProductList(context: Context): List<FoodCategory> {
        val jsonFileString = getJsonDataFromAsset(context, "foodProducts.json")
        val type = object : TypeToken<List<FoodCategory>>() {}.type
        return Gson().fromJson(jsonFileString, type)
    }

    private fun getToken(): String? {
        return sharedPref.getStringData(Constants.ACCESS_TOKEN)
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val token = getToken()
            if (token != null) {
                repository.getUserInfo(token = token).collectLatest { data ->
                    user.value = data
                }
            }
        }
    }

    fun getCartList() {
        viewModelScope.launch {
            repository.getCartList().collectLatest { data ->
                carts.value = data
            }
        }
    }

    private fun getReviews() {
        viewModelScope.launch {
            repository.getReviews().collectLatest { data ->
                reviews.value = data
            }
        }
    }

    private fun getMenus() {
        viewModelScope.launch {
            repository.getMenus().collectLatest { data ->
                menus.value = data
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