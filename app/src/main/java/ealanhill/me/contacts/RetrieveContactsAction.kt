package ealanhill.me.contacts

import ealanhill.me.contacts.network.models.Contact

object RetrieveContactsAction: Action {
    var contacts: List<Contact> = listOf()

    fun create(contacts: List<Contact>): RetrieveContactsAction {
        this.contacts = contacts
        return this
    }
}