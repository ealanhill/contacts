package ealanhill.me.contacts.network.models

import com.google.gson.annotations.SerializedName
import ealanhill.me.contacts.ContactsInterface

data class Contact(val name: String ="",
                   val id: Int = -1,
                   val companyName: String? = "",
                   var isFavorite: Boolean = false,
                   @SerializedName("smallImageURL") val smallImageUrl: String? = null,
                   @SerializedName("largeImageURL") val largeImageUrl: String? = null,
                   val emailAddress: String = "",
                   val birthdate: String = "",
                   val phone: Phone = Phone(),
                   val address: Address = Address()): ContactsInterface {
    override val type = ContactsInterface.Type.CONTACT

    companion object {
        val PHONE = "Phone"
        val ADDRESS = "Address"
        val BIRTHDATE = "Birthdate"
        val EMAIL = "Email"
    }
}