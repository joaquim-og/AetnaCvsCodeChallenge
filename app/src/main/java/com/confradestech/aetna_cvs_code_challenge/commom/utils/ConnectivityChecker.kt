package com.confradestech.aetna_cvs_code_challenge.commom.utils

import android.app.Application
import com.confradestech.aetna_cvs_code_challenge.commom.utils.extensions.hasInternet

class ConnectivityChecker(private val application: Application) {

    fun isConnected(): Boolean {
        return application.hasInternet()
    }
}
