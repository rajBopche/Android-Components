package com.example.androidplayground.utility

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.androidplayground.feature.users.repo.UserDatabase
import com.example.androidplayground.utility.network.ApiClient
import kotlinx.coroutines.Dispatchers

class UserRepository(context: Context) {

    private val userDao = UserDatabase.getDataBaseInstance(context).userDao()
    private val apiClient = ApiClient.getClient()

    /*val userList = resultLiveData(
        dbQuery = {
            userDao.getAllUsers()
        },
        networkCall = {

        },
        saveCallResult = {

        })*/

    private fun <T, A> resultLiveData(
        dbQuery: () -> LiveData<T>,
        networkCall: suspend () -> Result<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Result<T>> = liveData(Dispatchers.IO) {
        emit(Result.loading())
        val source = dbQuery().map { userItem: T -> Result.success(userItem) }
        emitSource(source)
        val responseStatus = networkCall()
        if (responseStatus.status == Result.Status.SUCCESS)
            saveCallResult(responseStatus.data!!)
        else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.message!!))
            emitSource(source)
        }

    }

    private suspend fun <T> fetchData(): Result<T> {
        return try {
            val response = apiClient.getUserData()
            if (response.isSuccessful) {
                val responseBody = response.body()
                //TODO 1/11/19 : ReCheck this casting
                return Result.success(responseBody as T)
            }
            Result.error("${response.code()} ${response.message()}")

        } catch (e: Exception) {
            Result.error("${e.stackTrace}")
        }
    }


}