package ealanhill.me.contacts.network.models

import com.google.gson.annotations.SerializedName

data class PhoneNumber(val type: PhoneNumber.Type, val number: String) {
    enum class Type(val string: String) {
        @SerializedName("work")
        WORK("work"),
        @SerializedName("home")
        HOME("home"),
        @SerializedName("mobile")
        MOBILE("mobile")
    }
}