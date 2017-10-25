package ealanhill.me.contacts

import dagger.Component
import ealanhill.me.contacts.overview.ContactsActionCreator
import ealanhill.me.contacts.overview.ContactsActivity
import ealanhill.me.contacts.overview.ContactsAdapter
import ealanhill.me.contacts.network.ApiModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class,
        ContextModule::class))
interface AppComponent {
    fun inject(contactsActionCreator: ContactsActionCreator)

    fun inject(contactsActivity: ContactsActivity)

    fun inject(adapter: ContactsAdapter)
}