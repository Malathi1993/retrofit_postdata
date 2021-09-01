package com.example.retrofit_postdata

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {
    @Headers("/v1/login/credentials")
    @POST("users")
    fun addUser(@Body userData: UserInfo): Call<UserInfoResponse>
}


