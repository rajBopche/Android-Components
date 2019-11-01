package com.example.androidplayground.feature.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidplayground.utility.Constants
import com.example.androidplayground.utility.UserRepository
import com.example.androidplayground.utility.extensions.safeLaunch

class UserViewModel(app: Application) : AndroidViewModel(app) {

    var userList: LiveData<List<UserData>> = MutableLiveData<List<UserData>>()
    private val userRepository = UserRepository(app)
    private val loadStatus = MutableLiveData<Int>()

    fun getData() {
        loadStatus.value = Constants.STATUS_LOADING
        viewModelScope.safeLaunch(
            {
                userList = userRepository.getUserList()
                loadStatus.postValue(Constants.STATUS_COMPLETE)
            }, {
                loadStatus.postValue(Constants.STATUS_ERROR)
            }
        )
    }

    fun getLoadingStatus(): LiveData<Int> = loadStatus

}