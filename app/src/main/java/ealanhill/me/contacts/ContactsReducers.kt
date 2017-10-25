package ealanhill.me.contacts

import ealanhill.me.contacts.detail.ContactDetail
import ealanhill.me.contacts.detail.ContactDetailAction
import ealanhill.me.contacts.detail.ContactInfoEntry
import ealanhill.me.contacts.detail.FavoriteAction
import ealanhill.me.contacts.network.models.Contact
import ealanhill.me.contacts.network.models.Phone
import ealanhill.me.contacts.overview.ContactsHeader
import ealanhill.me.contacts.overview.ContactsState
import ealanhill.me.contacts.overview.RetrieveContactsAction
import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object ContactsReducers {
    private val dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.US)

    fun reducers(): Reducer<Action, ContactsState> {
        return Reducers.matchClass<Action, ContactsState>()
                .`when`(RetrieveContactsAction::class.java, filterContacts())
                .`when`(ContactDetailAction::class.java, createContactDetail())
                .`when`(FavoriteAction::class.java, updateContact())
    }

    fun filterContacts(): Reducer<RetrieveContactsAction, ContactsState> {
        return Reducer { action, state ->
            val favoriteContacts = action.contacts
                    .filter { contact -> contact.isFavorite }
                    .sortedBy { contact -> contact.name }
            val otherContacts = action.contacts
                    .filterNot { contact -> contact.isFavorite }
                    .sortedBy { contact -> contact.name }

            val contacts = listOf<ContactsInterface>(ContactsHeader(R.string.header_favorites)) +
                    favoriteContacts +
                    listOf<ContactsInterface>(ContactsHeader(R.string.header_others)) +
                    otherContacts

            state.copy(contacts = contacts, rawContacts = action.contacts)
        }
    }

    fun createContactDetail(): Reducer<ContactDetailAction, ContactsState> {
        return Reducer { action, state ->
            val contact = action.contact
            val contactInfoEntry = mutableListOf<ContactInfoEntry>()

            contact.phone.apply {
                if (home.isNotEmpty()) {
                    contactInfoEntry.add(ContactInfoEntry(Contact.PHONE, home, Phone.Type.HOME.string))
                }
                if (mobile.isNotEmpty()) {
                    contactInfoEntry.add(ContactInfoEntry(Contact.PHONE, mobile, Phone.Type.MOBILE.string))
                }
                if (work.isNotEmpty()) {
                    contactInfoEntry.add(ContactInfoEntry(Contact.PHONE, work, Phone.Type.WORK.string))
                }
            }

            contactInfoEntry.add(ContactInfoEntry(Contact.ADDRESS, contact.address.format()))

            if (contact.birthdate.isNotEmpty()) {
                val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                contactInfoEntry.add(ContactInfoEntry(Contact.BIRTHDATE,
                        dateFormat.format(fmt.parse(contact.birthdate)).toString()))
            }

            if (contact.emailAddress.isNotEmpty()) {
                contactInfoEntry.add(ContactInfoEntry(Contact.EMAIL, contact.emailAddress))
            }

            state.copy(contactDetail = ContactDetail(contact.name,
                    contact.companyName ?: "",
                    contact.largeImageUrl,
                    contact.isFavorite,
                    contactInfoEntry))
        }
    }

    fun updateContact(): Reducer<FavoriteAction, ContactsState> {
        return Reducer { action, state ->
            val contactDetail = state.contactDetail
            val rawContacts = state.rawContacts
            contactDetail.isFavorite = action.isFavorite
            rawContacts.forEach { contact: Contact ->
                if (contact.name == contactDetail.contactName) {
                    contact.isFavorite = action.isFavorite
                }
            }

            val favoriteContacts = rawContacts
                    .filter { contact -> contact.isFavorite }
                    .sortedBy { contact -> contact.name }
            val otherContacts = rawContacts
                    .filterNot { contact -> contact.isFavorite }
                    .sortedBy { contact -> contact.name }

            val contacts = listOf<ContactsInterface>(ContactsHeader(R.string.header_favorites)) +
                    favoriteContacts +
                    listOf<ContactsInterface>(ContactsHeader(R.string.header_others)) +
                    otherContacts

            ContactsState(contacts = contacts, contactDetail = contactDetail, rawContacts = rawContacts)
        }
    }
}