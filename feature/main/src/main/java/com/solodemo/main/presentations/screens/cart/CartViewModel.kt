package com.solodemo.main.presentations.screens.cart

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.components.Constants
import com.solo.components.state.RequestState
import com.solo.util.SharedPreferenceHelper
import com.solodemo.main.presentations.screens.account.AccountState
import com.solodemo.supabase.domain.repository.SupabaseRepository
import com.solodemo.supabase.model.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: SupabaseRepository,
    application: Application
) : ViewModel() {


    private val _cartList = mutableStateOf(emptyList<Cart>())
    val cartList: State<List<Cart>> get() = _cartList

    var accountState by mutableStateOf(AccountState())
        private set

    private val _subTotalPrice = derivedStateOf {
        _cartList.value
            .filter { it.productPrice != null }
            .sumOf { it.productPrice!!.toDouble() }
    }
    val subTotalPrice: State<Double> get() = _subTotalPrice

    private val _deliveryFee = mutableDoubleStateOf(59.00)
    val deliveryFee: State<Double> get() = _deliveryFee

    private val _totalPrice = derivedStateOf {
        _subTotalPrice.value + _deliveryFee.doubleValue
    }
    val totalPrice: State<Double> get() = _totalPrice



    private val sharedPref = SharedPreferenceHelper(application.applicationContext)


    fun getCartList() {
        viewModelScope.launch {
            repository.getCartList().collectLatest { data ->

                if (data is RequestState.Success) {
                    _cartList.value = data.data
                }
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            val token = getToken()
            if (token != null) {
                repository.getUserInfo(token = token).collectLatest { data ->
                    if (data is RequestState.Success) {
                        accountState = accountState.copy(name = data.data.name)
                    }
                }
            }
        }
    }

    private fun getToken(): String? {
        return sharedPref.getStringData(Constants.ACCESS_TOKEN)
    }
}