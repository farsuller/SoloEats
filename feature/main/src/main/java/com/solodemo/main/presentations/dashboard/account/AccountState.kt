package com.solodemo.main.presentations.dashboard.account

import com.solo.components.Constants.DEFAULT_TEXT

data class AccountState(
    val name: String? = null,
    val email: String = DEFAULT_TEXT,
    val mobileNumber: String? = null,
    val address: String? = null,
)
