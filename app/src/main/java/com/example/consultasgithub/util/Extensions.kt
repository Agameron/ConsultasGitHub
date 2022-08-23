package com.example.consultasgithub.util

import android.content.Context
import android.graphics.Color
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun Context.loadCustomTabForSite(url: String, color: Int = Color.BLUE) {
    val customTabsIntent = CustomTabsIntent.Builder()
        .setToolbarColor(color)
        .setShowTitle(true)
        .build()

    customTabsIntent.launchUrl(this, Uri.parse(url))
}