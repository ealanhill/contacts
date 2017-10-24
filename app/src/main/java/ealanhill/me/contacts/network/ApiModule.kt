package ealanhill.me.contacts.network

import dagger.Module
import dagger.Provides
import ealanhill.me.contacts.firstPage.ContactsActionCreator
import javax.inject.Singleton

@Module
class ApiModule(private val contactsApi: ContactsApi) {

    private val contactsActionCreator: ContactsActionCreator

    init {
        contactsActionCreator = ContactsActionCreator(contactsApi)
    }

    @Provides
    @Singleton
    fun provideApi(): ContactsApi = contactsApi

    @Provides
    @Singleton
    fun provideContactsActionCreatore(): ContactsActionCreator = contactsActionCreator
}