package ealanhill.me.contacts.network.models

import ealanhill.me.contacts.ContactsInterface
import okhttp3.HttpUrl

data class Contact(val name: String ="",
                   val id: Int = -1,
                   val companyName: String = "",
                   val isFavorite: Boolean = false,
                   val smallImageUrl: HttpUrl? = null,
                   val largeImageUrl: HttpUrl? = null,
                   val emailAddress: String = "",
                   val birthdate: String = "",
                   val phone: Phone = Phone(),
                   val address: Address = Address()): ContactsInterface