package ealanhill.me.contacts

import ealanhill.me.contacts.network.models.Contact

data class ContactsState(val favoriteContacts: List<Contact> = listOf(),
                         val otherContacts: List<Contact> = listOf())