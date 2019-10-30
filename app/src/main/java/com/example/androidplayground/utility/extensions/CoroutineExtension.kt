package com.example.androidplayground.utility.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun <T> CoroutineScope.safeLaunch(job: suspend CoroutineScope.() -> T, errorCase: () -> Unit) {

    try {
        launch {
            job()
        }
    } catch (e: Exception) {
        errorCase()
    }
}
