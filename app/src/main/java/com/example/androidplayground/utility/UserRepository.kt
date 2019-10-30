package com.example.androidplayground.utility

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.androidplayground.feature.users.UserData
import com.example.androidplayground.feature.users.repo.UserDao
import com.example.androidplayground.feature.users.repo.UserDatabase
import com.example.androidplayground.utility.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {

    private var userDao: UserDao
    private var userList: LiveData<List<UserData>>? = null
    private val apiService = ApiClient.getClient()

    init {
        val userDatabase = UserDatabase.getDataBaseInstance(context)
        userDao = userDatabase.userDao()
    }

    suspend fun insert(user: UserData) {
        userDao.insertUser(user)
    }

    private suspend fun insertAllUsers(userList: List<UserData>?) {
        if (userList != null && userList.isNotEmpty()) {
            userDao.insertAllUsers(userList)
        }
    }

    suspend fun getUserList(): LiveData<List<UserData>> {
        val userListFromRemote = apiService.getUserData().body()
        withContext(Dispatchers.IO) {
            insertAllUsers(userListFromRemote)
        }
        return userDao.getAllUsers()
    }

}