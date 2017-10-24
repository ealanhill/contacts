package ealanhill.me.contacts.firstPage

import android.app.Application
import me.tatarka.redux.android.lifecycle.StoreAndroidViewModel

class ContactsViewModel(application: Application): StoreAndroidViewModel<ContactsState, ContactsStore>(application, ContactsStore())