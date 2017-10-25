package ealanhill.me.contacts.detail

data class ContactDetail(val contactName: String = "",
                         val company: String = "",
                         val image: String? = "",
                         val isFavorite: Boolean = false,
                         val infoEntry: List<ContactInfoEntry> = listOf())