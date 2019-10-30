package com.example.androidplayground.feature.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidplayground.utility.Constants
import com.example.androidplayground.utility.UserRepository
import com.example.androidplayground.utility.extensions.safeLaunch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {

    private val userList = MutableLiveData<List<UserData>>()
    private val userRepository = UserRepository(app)
    private val loadStatus = MutableLiveData<Int>()

    fun getData() {
        loadStatus.value = Constants.STATUS_LOADING
        /*GlobalScope.launch {
            try {
                val userData = userRepository.getUserList()
                userList.postValue(userData)
                loadStatus.postValue(Constants.STATUS_COMPLETE)
            } catch (e: Exception) {
                e.printStackTrace()
                loadStatus.postValue(Constants.STATUS_ERROR)
            }
        }*/
        viewModelScope.safeLaunch(
            {
                userList.postValue(userRepository.getUserList().value)
                loadStatus.postValue(Constants.STATUS_COMPLETE)
            }, { loadStatus.postValue(Constants.STATUS_ERROR) }
        )
    }

    fun insertUser(user: UserData) {
        loadStatus.value = Constants.STATUS_LOADING
        GlobalScope.launch {
            try {
                userRepository.insert(user)
                loadStatus.postValue(Constants.STATUS_COMPLETE)
            } catch (e: Exception) {
                e.printStackTrace()
                loadStatus.postValue(Constants.STATUS_ERROR)
            }
        }
    }

    fun getUserData(): LiveData<List<UserData>> = userList

    fun getLoadingStatus(): LiveData<Int> = loadStatus

}