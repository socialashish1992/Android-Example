package com.ascra.rebelfoods

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApiClient {

    var userService: UserService? = null

    fun userServiceInstance(): UserService{
        if (userService == null) {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(ApplicationConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

            userService = retrofit.create(UserService::class.java)
        }

        return userService!!
    }
}
