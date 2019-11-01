package com.example.androidplayground.feature.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.androidplayground.utility.UserRepository

class UserViewModel(app: Application) : AndroidViewModel(app) {

    private val userRepo = UserRepository(app.applicationContext)

    // val userList: LiveData<Result<List<UserData>>> = userRepo.
}