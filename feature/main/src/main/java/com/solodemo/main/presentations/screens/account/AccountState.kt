package com.solodemo.main.presentations.screens.account

data class AccountState(
    val name: String = "",
    val email: String = "",
    val profile: String = "",
    val isEmailVerified : Boolean = false,
    val mobileNumber: String = "",
    val address: String = "",

)