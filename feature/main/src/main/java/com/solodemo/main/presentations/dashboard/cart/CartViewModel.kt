package com.solodemo.main.presentations.dashboard.cart

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
import com.solo.components.SharedPreferenceHelper
import com.solo.components.generateRandomDigits
import com.solodemo.main.presentations.dashboard.account.AccountState
import com.solodemo.supabase.domain.repository.SupabaseRepository
import com.solodemo.supabase.domain.model.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: SupabaseRepository,
    application: Application,
) : ViewModel() {

    private var _cartList = mutableStateOf(emptyList<Cart>())
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

    init {
        getCartList()
        getUserInfo()
        accountState = accountState.copy(mobileNumber = generateRandomPhoneNumber())
        accountState = accountState.copy(address = "Metro Manila")
    }

    fun setCartList(carts: List<Cart>) {
        _cartList.value = carts
    }

    fun deleteAllCartItem(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            repository.deleteAllCartItem().collectLatest { data ->
                if (data is RequestState.Success) {
                    withContext(Dispatchers.Main) {
                        onSuccess("Success! Your item has been updated to your cart")
                    }
                } else if (data is RequestState.Error) {
                    withContext(Dispatchers.Main) {
                        onError(data.error.message.toString())
                    }
                }
            }
        }
    }
    fun updateCartById(
        id: Int,
        cart: Cart,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            repository.updateCartItem(id = id, cart = cart).collectLatest { data ->
                if (data is RequestState.Success) {
                    withContext(Dispatchers.Main) {
                        onSuccess("Success! Your item has been updated to your cart")
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

    fun deleteCartById(
        id: Int,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        viewModelScope.launch {
            repository.deleteCartItem(id = id).collectLatest { data ->
                if (data is RequestState.Success) {
                    withContext(Dispatchers.Main) {
                        onSuccess("Success! Your item has been deleted to your cart")
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

    private fun generateRandomPhoneNumber(): String {
        val countryCode = "+639"
        val remainingDigits = generateRandomDigits(9)

        return "$countryCode$remainingDigits"
    }

    private fun getCartList() {
        viewModelScope.launch {
            repository.getCartList().collectLatest { data ->
                if (data is RequestState.Success) {
                    _cartList.value = data.data
                }
            }
        }
    }

    private fun getUserInfo() {
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
