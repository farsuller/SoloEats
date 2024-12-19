package com.solo.components

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.core.content.pm.PackageInfoCompat
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import java.io.IOException
import java.text.NumberFormat
import java.util.Locale
import kotlin.random.Random

val NavHostController.canBackStack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun getJsonDataFromAsset(
    context: Context,
    fileName: String,
): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (exp: IOException) {
        exp.printStackTrace()
        return null
    }

    return jsonString
}

@Composable
fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit,
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.then(
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
        ),
    )
}

fun String.extractNameFromEmail(): String {
    return this.substringBefore("@")
}

fun getAppVersion(context: Context): String {
    return try {
        val packageManager: PackageManager = context.packageManager
        val packageInfo: PackageInfo = packageManager.getPackageInfo(context.packageName, 0)
        val versionCode: Long = PackageInfoCompat.getLongVersionCode(packageInfo)

        val versionName: String = packageInfo.versionName.toString()
        "v$versionCode.$versionName"
    } catch (e: PackageManager.NameNotFoundException) {
        "version N/A"
    }
}

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$"
    return email.matches(emailRegex.toRegex())
}

fun formatToCurrency(value: Double): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("en", "PH"))
    return currencyFormat.format(value)
}

fun sendEmail(
    toEmail: String,
    subject: String,
    message: String,
    context: Context,
) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$toEmail")
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, message)
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        // Handle the case where the user doesn't have an email app installed.
        // You can display a message or prompt the user to install an email app.
        // For simplicity, we'll just log a message.
        println("No email app installed on the device.")
    }
}

fun generateRandomDigits(length: Int): String {
    val random = Random(System.currentTimeMillis())
    val stringBuilder = StringBuilder()

    repeat(length) {
        val randomDigit = random.nextInt(10) // Generate a random digit (0-9)
        stringBuilder.append(randomDigit)
    }

    return stringBuilder.toString()
}
