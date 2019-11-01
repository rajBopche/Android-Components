package com.example.androidplayground.utility

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

class UserRepository(context: Context) {

    // val userList =

    private fun <T, A> resultLiveData(

        dbQuery: () -> LiveData<T>,
        networkCall: suspend () -> Result<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Result<T>> = liveData(Dispatchers.IO) {
        emit(Result.loading<T>())
        val source = dbQuery.invoke().map { Result.success(it) }
    }
}