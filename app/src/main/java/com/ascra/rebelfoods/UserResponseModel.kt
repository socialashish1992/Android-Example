package com.ascra.rebelfoods

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserResponseModel : Serializable {

    @Expose
    @SerializedName("company")
    var company: Company? = null
    @Expose
    @SerializedName("website")
    var website: String? = null
    @Expose
    @SerializedName("phone")
    var phone: String? = null
    @Expose
    @SerializedName("address")
    var address: Address? = null
    @Expose
    @SerializedName("email")
    var email: String? = null
    @Expose
    @SerializedName("username")
    var username: String? = null
    @Expose
    @SerializedName("name")
    var name: String? = null
    @Expose
    @SerializedName("isSelected")
    var isSelected: Boolean = false
    @Expose
    @SerializedName("id")
    var id: Int = 0

    class Company {
        @Expose
        @SerializedName("bs")
        var bs: String? = null
        @Expose
        @SerializedName("catchPhrase")
        var catchPhrase: String? = null
        @Expose
        @SerializedName("name")
        var name: String? = null
    }

    class Address {
        @Expose
        @SerializedName("geo")
        var geo: Geo? = null
        @Expose
        @SerializedName("zipcode")
        var zipcode: String? = null
        @Expose
        @SerializedName("city")
        var city: String? = null
        @Expose
        @SerializedName("suite")
        var suite: String? = null
        @Expose
        @SerializedName("street")
        var street: String? = null
    }

    class Geo {
        @Expose
        @SerializedName("lng")
        var lng: String? = null
        @Expose
        @SerializedName("lat")
        var lat: String? = null
    }
}
