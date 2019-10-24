package com.ascra.rebelfoods

import android.content.Context
import android.net.ConnectivityManager

object ApplicationConstant {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val PLACE = "place"

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}
