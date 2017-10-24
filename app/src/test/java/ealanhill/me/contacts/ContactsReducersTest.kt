package ealanhill.me.contacts

import ealanhill.me.contacts.firstPage.ContactsHeader
import ealanhill.me.contacts.firstPage.ContactsReducers
import ealanhill.me.contacts.firstPage.ContactsState
import ealanhill.me.contacts.firstPage.RetrieveContactsAction
import ealanhill.me.contacts.network.models.Contact
import junit.framework.Assert.assertTrue
import org.junit.Test

class ContactsReducersTest {

    @Test
    fun givenListShouldSortIntoFavoritesAndOthers() {
        val contacts = listOf(Contact(name = "a", isFavorite = true),
                Contact(name = "b", isFavorite = false),
                Contact(name="c", isFavorite = true),
                Contact(name="d", isFavorite = false))
        var contactsState = ContactsState()
        contactsState = ContactsReducers.filterContacts()
                .reduce(RetrieveContactsAction.create(contacts), contactsState)
        val contactsExpected = listOf(ContactsHeader(R.string.header_favorites),
                Contact(name = "a", isFavorite = true),
                Contact(name="c", isFavorite = true),
                ContactsHeader(R.string.header_others),
                Contact(name = "b", isFavorite = false),
                Contact(name="d", isFavorite = false))
        assertTrue(contactsState.contacts == contactsExpected)
    }

    @Test
    fun givenListShouldAlphabeticallySort() {
        val contacts = listOf(Contact(name = "b", isFavorite = false),
                Contact(name="d", isFavorite = false),
                Contact(name = "a", isFavorite = false),
                Contact(name="c", isFavorite = false))
        var contactsState = ContactsState()
        contactsState = ContactsReducers.filterContacts()
                .reduce(RetrieveContactsAction.create(contacts), contactsState)

        val contactsExpected = listOf(ContactsHeader(R.string.header_favorites),
                ContactsHeader(R.string.header_others),
                Contact(name = "a", isFavorite = false),
                Contact(name = "b", isFavorite = false),
                Contact(name="c", isFavorite = false),
                Contact(name="d", isFavorite = false))

        assertTrue(contactsState.contacts == contactsExpected)
    }

    @Test
    fun givenListShouldAlphabeticallyAndFavoriteSort() {
        val contacts = listOf(Contact(name = "b", isFavorite = true),
                Contact(name="d", isFavorite = false),
                Contact(name = "a", isFavorite = true),
                Contact(name="c", isFavorite = false),
                Contact(name = "f", isFavorite = true),
                Contact(name = "m", isFavorite = false),
                Contact(name = "l", isFavorite = true))
        var contactsState = ContactsState()
        contactsState = ContactsReducers.filterContacts()
                .reduce(RetrieveContactsAction.create(contacts), contactsState)

        val contactsExpected = listOf(ContactsHeader(R.string.header_favorites),
                Contact(name = "a", isFavorite = true),
                Contact(name = "b", isFavorite = true),
                Contact(name = "f", isFavorite = true),
                Contact(name = "l", isFavorite = true),
                ContactsHeader(R.string.header_others),
                Contact(name="c", isFavorite = false),
                Contact(name="d", isFavorite = false),
                Contact(name = "m", isFavorite = false))

        assertTrue(contactsState.contacts == contactsExpected)
    }
}