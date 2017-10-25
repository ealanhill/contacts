package ealanhill.me.contacts.overview

import ealanhill.me.contacts.ContactsInterface
import ealanhill.me.contacts.detail.ContactDetail

data class ContactsState(val contacts: List<ContactsInterface> = listOf(),
                         val contactDetail: ContactDetail = ContactDetail())