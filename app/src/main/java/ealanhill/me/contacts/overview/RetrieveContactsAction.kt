package ealanhill.me.contacts.overview

import ealanhill.me.contacts.Action
import ealanhill.me.contacts.network.models.Contact

/**
 * Action to indicate the app should retrieve the contacts from the server
 */
object RetrieveContactsAction: Action {
    var contacts: List<Contact> = listOf()

    fun create(contacts: List<Contact>): RetrieveContactsAction {
        RetrieveContactsAction.contacts = contacts
        return this
    }
}