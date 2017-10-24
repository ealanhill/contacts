package ealanhill.me.contacts.network

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule(val contactsApi: ContactsApi) {

    @Provides
    @Singleton
    fun provideApi(): ContactsApi = contactsApi
}