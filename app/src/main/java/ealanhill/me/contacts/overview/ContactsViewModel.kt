package ealanhill.me.contacts.overview

import android.app.Application
import ealanhill.me.contacts.ContactsStore
import me.tatarka.redux.android.lifecycle.StoreAndroidViewModel

class ContactsViewModel(application: Application): StoreAndroidViewModel<ContactsState, ContactsStore>(application, ContactsStore())