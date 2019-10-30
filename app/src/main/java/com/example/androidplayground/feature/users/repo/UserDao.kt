package com.example.androidplayground.feature.users.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidplayground.feature.users.UserData

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserData)

    @Insert
    suspend fun insertAllUsers(userList: List<UserData>)

    @Query("SELECT * from user_table")
    fun getAllUsers(): LiveData<List<UserData>>
}