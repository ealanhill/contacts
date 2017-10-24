package ealanhill.me.contacts

import dagger.Component
import ealanhill.me.contacts.network.ApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface AppComponent {
    fun inject(contactsActionCreator: ContactsActionCreator)
}