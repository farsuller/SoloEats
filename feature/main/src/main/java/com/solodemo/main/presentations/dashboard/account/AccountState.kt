package com.solodemo.main.presentations.dashboard.account

import com.solo.components.Constants.DEFAULT_TEXT

data class AccountState(
    val name: String = DEFAULT_TEXT,
    val email: String = DEFAULT_TEXT,
    val profile: String = "",
    val isEmailVerified : Boolean = false,
    val mobileNumber: String = DEFAULT_TEXT,
    val address: String = DEFAULT_TEXT,

)