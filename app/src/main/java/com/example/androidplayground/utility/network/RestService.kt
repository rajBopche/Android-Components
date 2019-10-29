package com.example.androidplayground.utility.network

import com.example.androidplayground.feature.users.UserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RestService {

    @GET
    suspend fun getUserData(@Url url: String = "https://api.github.com/users"): Response<List<UserData>>

}