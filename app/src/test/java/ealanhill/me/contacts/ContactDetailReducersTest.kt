package ealanhill.me.contacts

import ealanhill.me.contacts.detail.ContactDetail
import ealanhill.me.contacts.detail.ContactDetailAction
import ealanhill.me.contacts.detail.ContactDetailReducers
import ealanhill.me.contacts.detail.ContactInfoEntry
import ealanhill.me.contacts.network.models.Address
import ealanhill.me.contacts.network.models.Contact
import ealanhill.me.contacts.network.models.Phone
import ealanhill.me.contacts.network.models.PhoneNumber
import ealanhill.me.contacts.overview.ContactsState
import junit.framework.Assert.assertTrue
import org.junit.Test

class ContactDetailReducersTest {

    @Test
    fun givenAContactShouldFormatTheDetailCorrectly() {
        val contact = Contact(name = "name",
                id = 1,
                companyName = "company",
                isFavorite = true,
                smallImageUrl = "http://example.com/1",
                largeImageUrl = "http://example.com/2",
                emailAddress = "example@gmail.com",
                birthdate = "1987-12-02",
                phone = Phone(listOf(PhoneNumber(PhoneNumber.Type.HOME, "555-555-5555"),
                        PhoneNumber(PhoneNumber.Type.MOBILE, "123-123-1234"))),
                address = Address(street = "123 Any St", city = "Any City", state = "IL", country = "US", zip = 12345))

        var contactsState = ContactsState()

        contactsState = ContactDetailReducers.createContactDetail()
                .reduce(ContactDetailAction(contact), contactsState)

        val contactDetailExpected = ContactDetail(contactName = "name",
                company = "company",
                image = "http://example.com/2",
                isFavorite = true,
                infoEntry = listOf(
                        ContactInfoEntry(infoType = "Phone", info = "555-555-5555", extra = "Home"),
                        ContactInfoEntry(infoType = "Phone", info = "123-123-1234", extra = "Mobile"),
                        ContactInfoEntry(infoType = "Address", info = "123 Any St\nAny City, IL 12345, US"),
                        ContactInfoEntry(infoType = "Birthdate", info = "December 2, 1987"),
                        ContactInfoEntry(infoType = "Email", info = "example@gmail.com")
                ))

        assertTrue("Unable to create the appropriate formatting",
                contactsState.contactDetail == contactDetailExpected)
    }
}