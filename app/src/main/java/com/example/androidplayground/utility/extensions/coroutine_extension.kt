package com.example.androidplayground.utility.extensions

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun safeLaunch(
    block: suspend () -> Unit
) {

    try {
        GlobalScope.launch {
            block()
        }
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}