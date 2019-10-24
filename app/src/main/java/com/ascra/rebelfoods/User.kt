package com.ascra.rebelfoods

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class User : Serializable {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    var uid: Int = 0
    @ColumnInfo(name = "name")
    var name: String? = null
    @ColumnInfo(name = "email")
    var email: String? = null
    @ColumnInfo(name = "address")
    var address: String? = null
    @ColumnInfo(name = "company")
    var company: String? = null
    @ColumnInfo(name = "latitude")
    var latitude: String? = null
    @ColumnInfo(name = "longitude")
    var longitude: String? = null
}
