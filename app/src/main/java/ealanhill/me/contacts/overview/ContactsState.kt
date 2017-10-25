package ealanhill.me.contacts.overview

import ealanhill.me.contacts.ContactsInterface
import ealanhill.me.contacts.detail.ContactDetail
import ealanhill.me.contacts.network.models.Contact

data class ContactsState(val contacts: List<ContactsInterface> = listOf(),
                         val contactDetail: ContactDetail = ContactDetail(),
                         val rawContacts: List<Contact> = listOf())