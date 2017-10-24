package ealanhill.me.contacts

import ealanhill.me.contacts.network.ContactsApi
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ContactsApiTest {

    private val contactsApi: ContactsApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://s3.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        contactsApi = retrofit.create(ContactsApi::class.java)
    }

    @Test
    fun whenWeRetrieveJsonResultShouldNotBeNull() {
        val contacts = contactsApi.retrieveContacts().execute()
        Assert.assertTrue("unable to retrieve contacts", contacts.body() != null)
    }
}