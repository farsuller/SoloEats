package com.solodemo.main.presentations

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.solo.components.Constants
import com.solo.components.getJsonDataFromAsset
import com.solo.components.state.RequestState
import com.solodemo.database.SharedPreferenceHelper
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.presentations.dashboard.account.AccountState
import com.solodemo.supabase.domain.model.Cart
import com.solodemo.supabase.domain.repository.Carts
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews
import com.solodemo.supabase.domain.repository.SupabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SupabaseRepository,
    application: Application,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RequestState<*>>(RequestState.Loading)
    val uiState: StateFlow<RequestState<*>>
        get() = _uiState

    val menus: MutableState<Menus> = mutableStateOf(RequestState.Idle)
    val reviews: MutableState<Reviews> = mutableStateOf(RequestState.Idle)
    var carts: MutableState<Carts> = mutableStateOf(RequestState.Idle)

    private val _cartListCount = mutableIntStateOf(0)
    val cartListCount: State<Int> get() = _cartListCount

    var accountState by mutableStateOf(AccountState())
        private set

    private val sharedPref = SharedPreferenceHelper(application.applicationContext)

    fun insertCart(
        cart: Cart,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            repository.insertCart(cart = cart).collectLatest { data ->
                if (data is RequestState.Success) {
                    withContext(Dispatchers.Main) {
                        onSuccess()
                        getCartList()
                    }
                } else if (data is RequestState.Error) {
                    withContext(Dispatchers.Main) {
                        onError(data.error.message.toString())
                    }
                }
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

    fun getUserInfo() {
        viewModelScope.launch {
            val token = getToken()
            if (token != null) {
                repository.getUserInfo(token = token).collectLatest { data ->
                    if (data is RequestState.Success) {
                        accountState = accountState.copy(name = data.data.name)
                        accountState = accountState.copy(email = data.data.email)
                        accountState = accountState.copy(profile = data.data.picture)
                        accountState = accountState.copy(isEmailVerified = data.data.emailVerified)
                    }
                }
            }
        }
    }

    fun getCartList() {
        viewModelScope.launch {
            repository.getCartList().collectLatest { data ->
                carts.value = data
                if (data is RequestState.Success) {
                    _cartListCount.intValue = data.data.size
                }
            }
        }
    }

    fun getReviews() {
        viewModelScope.launch {
            repository.getReviews().collectLatest { data ->
                reviews.value = data
            }
        }
    }

    fun getMenus() {
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
