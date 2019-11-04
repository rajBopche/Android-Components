package com.example.androidplayground.utility

import android.content.Context
import com.example.androidplayground.feature.users.UserData
import com.example.androidplayground.feature.users.repo.UserDao
import com.example.androidplayground.feature.users.repo.UserDatabase
import com.example.androidplayground.utility.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {

    private var userDao: UserDao
    private var userList: List<UserData>? = null
    private val apiService = ApiClient.getClient()

    init {
        val userDatabase = UserDatabase.getDataBaseInstance(context)
        userDao = userDatabase.userDao()
    }

    suspend fun insert(user: UserData) {
        userDao.insertUser(user)
    }

    suspend fun getUserList(): List<UserData> {
        userList = userDao.getAllUsers()
        if (userList.isNullOrEmpty()) {
            userList = apiService.getUserData().body()

            withContext(Dispatchers.IO) {
                userList?.forEach {
                    insert(it)
                }
            }
        }
        return userList!!
    }
}