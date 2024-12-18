package com.solodemo.main.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.solo.components.state.AuthState
import com.solodemo.database.domain.model.Cart
import com.solodemo.database.domain.usecase.CartUseCases
import com.solodemo.main.presentations.dashboard.account.AccountState
import com.solodemo.main.presentations.dashboard.cart.CartState
import com.solodemo.main.presentations.dashboard.home.HomeEvent
import com.solodemo.main.presentations.dashboard.home.ReviewsState
import com.solodemo.main.presentations.dashboard.menu.MenusState
import com.solodemo.main.presentations.products.ProductsState
import com.solodemo.network.data.ApiResult
import com.solodemo.network.domain.usecase.EatsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cartUseCases: CartUseCases,
    private val eatsUseCases: EatsUseCases,
) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _menusState = MutableStateFlow(MenusState())
    val menusState: StateFlow<MenusState> = _menusState.asStateFlow()

    private val _reviewsState = MutableStateFlow(ReviewsState())
    val reviewsState: StateFlow<ReviewsState> = _reviewsState.asStateFlow()

    private val _productsState = MutableStateFlow(ProductsState())
    val productsState: StateFlow<ProductsState> = _productsState.asStateFlow()

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()

    private val _accountState = MutableStateFlow(AccountState())
    val accountState: StateFlow<AccountState> = _accountState.asStateFlow()

    private val _isLoadingData = MutableStateFlow(false)
    val isLoadingData = _isLoadingData
        .onStart {
            requestApis()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    fun onEvent(event: HomeEvent, onSuccess: () -> Unit = {}) {
        when (event) {
            is HomeEvent.UpsertCartItem -> {
                insertCart(event.cartItem)
                onSuccess()
            }
        }
    }

    private fun insertCart(cart: Cart) = viewModelScope.launch {
        cartUseCases.upsertCart(cart = cart)
    }

    fun requestApis() {
        _isLoadingData.value = true
        getMenus()
        getReviews()
        getProducts()
        getCartList()
        _isLoadingData.value = false
    }

    private fun getCartList() {
        cartUseCases.getCartList().onEach { item ->
            _cartState.update { it.copy(cartList = item.asReversed()) }
        }.launchIn(viewModelScope)
    }

    private fun getProducts() = viewModelScope.launch {
        eatsUseCases.getProducts()
            .onStart { _productsState.update { it.copy(isLoading = true) } }
            .catch { e ->
                _productsState.update { it.copy(errorMessage = e.message, isLoading = false) }
            }
            .collectLatest { response ->
                when (response) {
                    is ApiResult.Success -> {
                        _productsState.update { it.copy(productsList = response.result.data, isLoading = false) }
                    }
                    is ApiResult.Error -> _productsState.update { it.copy(errorMessage = response.message, isLoading = false) }
                }
            }
    }

    private fun getReviews() = viewModelScope.launch {
        eatsUseCases.getReviews()
            .onStart { _reviewsState.update { it.copy(isLoading = true) } }
            .catch { e ->
                _reviewsState.update { it.copy(errorMessage = e.message, isLoading = false) }
            }
            .collectLatest { response ->
                when (response) {
                    is ApiResult.Success -> {
                        _reviewsState.update {
                            it.copy(reviewsList = response.result.data, isLoading = false)
                        }
                    }

                    is ApiResult.Error -> _reviewsState.update { it.copy(errorMessage = response.message, isLoading = false) }
                }
            }
    }

    private fun getMenus() = viewModelScope.launch {
        eatsUseCases.getMenus()
            .onStart { _menusState.update { it.copy(isLoading = true) } }
            .catch { e ->
                _menusState.update { it.copy(errorMessage = e.message, isLoading = false) }
            }
            .collectLatest { response ->
                when (response) {
                    is ApiResult.Success -> {
                        _menusState.update { it.copy(menusList = response.result.data, isLoading = false) }
                    }

                    is ApiResult.Error -> _menusState.update { it.copy(errorMessage = response.message, isLoading = false) }
                }
            }
    }

    fun logOut(onSuccess: () -> Unit) {
        auth.signOut()
        onSuccess()
        _authState.value = AuthState.Unauthenticated
    }
}
