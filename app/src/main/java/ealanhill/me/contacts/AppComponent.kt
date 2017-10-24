package ealanhill.me.contacts

import dagger.Component
import ealanhill.me.contacts.firstPage.ContactsActionCreator
import ealanhill.me.contacts.firstPage.ContactsActivity
import ealanhill.me.contacts.network.ApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class,
        ContextModule::class))
interface AppComponent {
    fun inject(contactsActionCreator: ContactsActionCreator)

    fun inject(contactsActivity: ContactsActivity)

    fun inject(adapter: ContactsActivity.ContactsAdapter)
}