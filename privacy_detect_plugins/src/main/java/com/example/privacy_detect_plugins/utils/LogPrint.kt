package com.example.privacy_detect_plugins.utils

import java.util.concurrent.Executors
internal object LogPrint {

    private val logThreadExecutor = Executors.newSingleThreadExecutor()

    fun log(log: Any?) {
        logThreadExecutor.submit {
            println(log)
        }
    }

}