package ealanhill.me.contacts

data class ContactsHeader(val text: Int): ContactsInterface {
    override val type = ContactsInterface.Type.HEADER
}