package ealanhill.me.contacts

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContactsModule(private val contactsActionCreator: ContactsActionCreator) {

    @Provides
    @Singleton
    fun provideContactsActionCreator() = contactsActionCreator
}