package ealanhill.me.contacts.detail

import android.text.TextUtils
import ealanhill.me.contacts.Action
import ealanhill.me.contacts.network.models.Contact
import ealanhill.me.contacts.network.models.PhoneNumber
import ealanhill.me.contacts.overview.ContactsState
import me.tatarka.redux.Reducer
import me.tatarka.redux.Reducers
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object ContactDetailReducers {

    val dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);

    fun reducers(): Reducer<Action, ContactsState> {
        return Reducers.matchClass<Action, ContactsState>()
                .`when`(ContactDetailAction::class.java, createContactDetail())
    }

    fun createContactDetail(): Reducer<ContactDetailAction, ContactsState> {
        return Reducer { action, state ->
            val contact = action.contact
            val contactInfoEntry = mutableListOf<ContactInfoEntry>()

            contact.phone.numbers.forEach { phoneNumber: PhoneNumber ->
                if (phoneNumber.number.isNotEmpty()) {
                    contactInfoEntry.add(ContactInfoEntry(Contact.PHONE, phoneNumber.number, phoneNumber.type.string))
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
                    contact.companyName,
                    contact.largeImageUrl,
                    contact.isFavorite,
                    contactInfoEntry))
        }
    }

    private fun isEmpty(string: String?): Boolean = string == null && string == ""
}