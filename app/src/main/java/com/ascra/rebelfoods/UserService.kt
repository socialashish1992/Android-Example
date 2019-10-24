package com.ascra.rebelfoods

import java.util.ArrayList

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @get:GET("users")
    val allUserFromApi: Call<ArrayList<UserResponseModel>>
}
