package ealanhill.me.contacts.overview

import ealanhill.me.contacts.ContactsInterface

/**
 * Header to divide the favorite and other sections
 */
data class ContactsHeader(val text: Int): ContactsInterface {
    override val type = ContactsInterface.Type.HEADER
}