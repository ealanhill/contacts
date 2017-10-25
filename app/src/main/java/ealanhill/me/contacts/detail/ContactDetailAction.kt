package ealanhill.me.contacts.detail

import ealanhill.me.contacts.Action
import ealanhill.me.contacts.network.models.Contact

/**
 * Action to indicate the user has selected a contact to show the detail view
 */
data class ContactDetailAction(val contact: Contact): Action