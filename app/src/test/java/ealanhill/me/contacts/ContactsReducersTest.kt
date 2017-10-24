package ealanhill.me.contacts

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
        assertTrue(contactsState.favoriteContacts == listOf(
                Contact(name = "a", isFavorite = true),
                Contact(name="c", isFavorite = true)))
        assertTrue(contactsState.otherContacts == listOf(
                Contact(name = "b", isFavorite = false),
                Contact(name="d", isFavorite = false)))
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
        assertTrue(contactsState.otherContacts == listOf(Contact(name = "a", isFavorite = false),
                Contact(name = "b", isFavorite = false),
                Contact(name="c", isFavorite = false),
                Contact(name="d", isFavorite = false)))
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
        assertTrue(contactsState.favoriteContacts == listOf(
                Contact(name = "a", isFavorite = true),
                Contact(name = "b", isFavorite = true),
                Contact(name = "f", isFavorite = true),
                Contact(name = "l", isFavorite = true)
        ))
        assertTrue(contactsState.otherContacts == listOf(
                Contact(name="c", isFavorite = false),
                Contact(name="d", isFavorite = false),
                Contact(name = "m", isFavorite = false)
        ))
    }
}