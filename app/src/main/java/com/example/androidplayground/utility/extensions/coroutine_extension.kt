package com.example.androidplayground.utility.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.safeLaunch(job: suspend CoroutineScope.() -> Job, errorCase: () -> Unit) {

    try {
        launch {
            job()
        }
    } catch (e: Exception) {
        errorCase()
    }
}