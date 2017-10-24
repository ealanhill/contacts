package ealanhill.me.contacts.firstPage

import ealanhill.me.contacts.Action
import ealanhill.me.contacts.network.models.Contact

object RetrieveContactsAction: Action {
    var contacts: List<Contact> = listOf()

    fun create(contacts: List<Contact>): RetrieveContactsAction {
        RetrieveContactsAction.contacts = contacts
        return this
    }
}