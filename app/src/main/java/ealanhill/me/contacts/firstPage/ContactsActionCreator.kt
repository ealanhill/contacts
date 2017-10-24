package ealanhill.me.contacts.firstPage

import android.util.Log
import ealanhill.me.contacts.Action
import ealanhill.me.contacts.App
import ealanhill.me.contacts.network.ContactsApi
import ealanhill.me.contacts.network.models.Contact
import me.tatarka.redux.Thunk
import javax.inject.Inject

class ContactsActionCreator {

    @Inject
    lateinit var contactsApi: ContactsApi

    init {
        App.COMPONENT.inject(this)
    }

    fun retrieveContacts(): Thunk<Action, Action> {
        return Thunk { dispatcher ->
            contactsApi.retrieveContacts()
                    .enqueue(object : retrofit2.Callback<List<Contact>> {
                        override fun onFailure(call: retrofit2.Call<List<Contact>>?, t: Throwable?) {
                            Log.e("ContactsActionCreator", "Unable to retrieve contacts list", t)
                        }

                        override fun onResponse(call: retrofit2.Call<List<Contact>>?,
                                                response: retrofit2.Response<List<Contact>>?) {
                            response?.apply {
                                dispatcher.dispatch(RetrieveContactsAction.create(body()!!))
                            }
                        }
                    })
        }
    }
}