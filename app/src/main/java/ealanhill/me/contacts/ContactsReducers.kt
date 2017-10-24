package ealanhill.me.contacts

import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers

object ContactsReducers {
    fun reducers(): Reducer<Action, ContactsState> {
        return Reducers.matchClass<Action, ContactsState>()
                .`when`(RetrieveContactsAction::class.java, filterContacts())
    }

    fun filterContacts(): Reducer<RetrieveContactsAction, ContactsState> {
        return Reducer { action, state ->
            val favoriteContacts = action.contacts
                    .filter { contact -> contact.isFavorite }
                    .sortedBy { contact -> contact.name }
            val otherContacts = action.contacts
                    .filterNot { contact -> contact.isFavorite }
                    .sortedBy { contact -> contact.name }
            state.copy(favoriteContacts = favoriteContacts,
                    otherContacts = otherContacts)
        }
    }
}