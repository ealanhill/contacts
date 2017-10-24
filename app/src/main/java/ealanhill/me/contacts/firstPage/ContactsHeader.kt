package ealanhill.me.contacts.firstPage

import ealanhill.me.contacts.ContactsInterface

data class ContactsHeader(val text: Int): ContactsInterface {
    override val type = ContactsInterface.Type.HEADER
}