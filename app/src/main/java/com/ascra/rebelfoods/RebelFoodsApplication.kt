package com.ascra.rebelfoods

import android.app.Application

import androidx.room.Room

class RebelFoodsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room
                .databaseBuilder(applicationContext, AppDatabase::class.java, "RebelFoods")
                .allowMainThreadQueries()
                .build()
    }

    companion object {
        lateinit var db: AppDatabase

        fun getDB():AppDatabase{
            return db
        }
    }
}
