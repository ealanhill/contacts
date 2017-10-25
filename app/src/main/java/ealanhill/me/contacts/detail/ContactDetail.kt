package ealanhill.me.contacts.detail

data class ContactDetail(val contactName: String = "",
                         val company: String = "",
                         val image: String? = "",
                         var isFavorite: Boolean = false,
                         val infoEntry: List<ContactInfoEntry> = listOf())