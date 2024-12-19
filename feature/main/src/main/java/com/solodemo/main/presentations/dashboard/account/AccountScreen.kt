package com.solodemo.main.presentations.dashboard.account

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.solo.components.Constants
import com.solo.components.R
import com.solo.components.getAppVersion
import com.solo.components.sendEmail
import com.solodemo.main.presentations.dashboard.account.components.AccountHeader
import com.solodemo.main.presentations.dashboard.account.components.AccountItemSubHeader
import com.solodemo.main.presentations.dashboard.account.components.AccountItems
import com.solodemo.main.presentations.dashboard.account.components.AppVersionItem
import com.solodemo.main.presentations.dashboard.account.components.LogOutButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun AccountScreen(
    onSignOutButtonClicked: () -> Unit,
    paddingValues: PaddingValues,
    accountState: AccountState,
    onPrivacyPolicyClicked: () -> Unit,
) {
    val context = LocalContext.current
    val appVersion = getAppVersion(context)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(bottom = paddingValues.calculateBottomPadding()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            AccountHeader(accountState = accountState)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                AccountItemSubHeader(R.string.my_account)

                AccountItems(text = R.string.favourites, onItemClicked = {
                    Toast.makeText(context, "Development in progress", Toast.LENGTH_SHORT).show()
                })

                AccountItems(text = R.string.saved_places, onItemClicked = {
                    Toast.makeText(context, "Development in progress", Toast.LENGTH_SHORT).show()
                })

                AccountItems(text = R.string.coupons, onItemClicked = {
                    Toast.makeText(context, "Development in progress", Toast.LENGTH_SHORT).show()
                })

                Spacer(modifier = Modifier.size(20.dp))

                AccountItemSubHeader(R.string.general)

                AccountItems(text = R.string.privacy_policy, onItemClicked = onPrivacyPolicyClicked)

                AccountItems(text = R.string.share_feedback, onItemClicked = {
                    sendEmail(
                        Constants.DEVELOPER_EMAIL,
                        Constants.EMAIL_SUBJECT,
                        Constants.EMAIL_MESSAGE,
                        context,
                    )
                })

                AppVersionItem(appVersion = appVersion)
            }
            LogOutButton(onSignOutButtonClicked = onSignOutButtonClicked)
        }
    }
}
