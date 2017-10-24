package ealanhill.me.contacts

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import ealanhill.me.contacts.databinding.ActivityContactsBinding
import javax.inject.Inject

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var store: ContactsStore

    @Inject
    lateinit var actionCreator: ContactsActionCreator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactsViewModel = ViewModelProviders.of(this@ContactsActivity)
                .get(ContactsViewModel::class.java)
        store = contactsViewModel.store
        App.COMPONENT.inject(this)
        store.dispatch(actionCreator.retrieveContacts())
        binding = DataBindingUtil.setContentView<ActivityContactsBinding>(this, R.layout.activity_contacts)
                .apply {
                    contactsRecyclerView.layoutManager = LinearLayoutManager(this@ContactsActivity)
                }
    }
}
