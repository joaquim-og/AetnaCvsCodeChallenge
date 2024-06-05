package com.confradestech.aetna_cvs_code_challenge.commom.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


fun Context.hasInternet(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager?.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ||
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}