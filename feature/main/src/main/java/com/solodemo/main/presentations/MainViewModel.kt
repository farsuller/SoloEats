package com.solodemo.main.presentations

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.solo.components.Constants
import com.solo.components.getJsonDataFromAsset
import com.solo.components.state.RequestState
import com.solodemo.database.SharedPreferenceHelper
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.usecase.CartUseCases
import com.solodemo.main.model.FoodCategory
import com.solodemo.main.presentations.dashboard.account.AccountState
import com.solodemo.main.presentations.dashboard.cart.CartState
import com.solodemo.main.presentations.dashboard.home.HomeEvent
import com.solodemo.supabase.domain.repository.Menus
import com.solodemo.supabase.domain.repository.Reviews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
    application: Application,
) : ViewModel() {

    val menus: MutableState<Menus> = mutableStateOf(RequestState.Idle)
    val reviews: MutableState<Reviews> = mutableStateOf(RequestState.Idle)

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    private val _accountState = MutableStateFlow(AccountState())
    val accountState: StateFlow<AccountState> = _accountState.asStateFlow()

    private val sharedPref = SharedPreferenceHelper(application.applicationContext)

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpsertCartItem -> {
                insertCart(event.cartItem)
            }
        }
    }

    private fun insertCart(cart: Cart) = viewModelScope.launch {
        cartUseCases.upsertCart(cart = cart)
    }

    fun getProductList(context: Context): List<FoodCategory> {
        val jsonFileString = getJsonDataFromAsset(context, "foodProducts.json")
        val type = object : TypeToken<List<FoodCategory>>() {}.type
        return Gson().fromJson(jsonFileString, type)
    }

    private fun getToken(): String? {
        return sharedPref.getStringData(Constants.ACCESS_TOKEN)
    }

//    fun getUserInfo() {
//        viewModelScope.launch {
//            val token = getToken()
//            if (token != null) {
//                repository.getUserInfo(token = token).collectLatest { data ->
//                    if (data is RequestState.Success) {
//                        accountState = accountState.copy(name = data.data.name)
//                        accountState = accountState.copy(email = data.data.email)
//                        accountState = accountState.copy(profile = data.data.picture)
//                        accountState = accountState.copy(isEmailVerified = data.data.emailVerified)
//                    }
//                }
//            }
//        }
//    }

    fun getCartList() {
        cartUseCases.getCartList().onEach { item ->
            _cartState.update {
                it.copy(cartList = item.asReversed())
            }
        }.launchIn(viewModelScope)
    }

//    fun getReviews() {
//        viewModelScope.launch {
//            repository.getReviews().collectLatest { data ->
//                reviews.value = data
//            }
//        }
//    }
//
//    fun getMenus() {
//        viewModelScope.launch {
//            repository.getMenus().collectLatest { data ->
//                menus.value = data
//            }
//        }
//    }
//
//    fun signOut() {
//        viewModelScope.launch {
//            repository.signOut().collectLatest { data ->
//                _uiState.update { data }
//                sharedPref.clearPreferences()
//            }
//        }
//    }
}
