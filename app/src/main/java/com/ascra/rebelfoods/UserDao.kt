package com.ascra.rebelfoods

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @get:Query("SELECT * FROM User")
    val allUser: List<User>

    @Query("SELECT * FROM User WHERE user_id=:uid")
    fun getUserById(uid: Int): User

    @Insert
    fun insertUser(users: User): Long

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user WHERE user_id = :uid")
    fun deleteUserBydId(uid: Int)

    @Update
    fun updateUser(user: User)
}
