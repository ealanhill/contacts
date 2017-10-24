package ealanhill.me.contacts.network

import ealanhill.me.contacts.network.models.Contact
import retrofit2.Call
import retrofit2.http.GET

interface ContactsApi {
    @GET("technical-challenge/v3/contacts.json")
    fun retrieveContacts(): Call<List<Contact>>
}