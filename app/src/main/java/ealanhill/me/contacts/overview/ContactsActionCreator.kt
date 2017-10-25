package ealanhill.me.contacts.overview

import android.util.Log
import ealanhill.me.contacts.Action
import ealanhill.me.contacts.network.ContactsApi
import ealanhill.me.contacts.network.models.Contact
import me.tatarka.redux.Thunk

/**
 * Creates the actions to retrieve data from the server
 */
class ContactsActionCreator(private val contactsApi: ContactsApi) {

    /**
     * Retrieves the contact list from the server
     */
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