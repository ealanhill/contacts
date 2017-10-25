package ealanhill.me.contacts.overview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import ealanhill.me.contacts.*
import ealanhill.me.contacts.databinding.ActivityContactsBinding
import ealanhill.me.contacts.network.models.Contact
import javax.inject.Inject

class ContactsActivity : AppCompatActivity(), ContactsAdapter.ContactsOnClickListener {

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
                    contactsRecyclerView.adapter = ContactsAdapter(store.state.contacts, this@ContactsActivity)
                }

        contactsViewModel.state.observe(this, Observer { data ->
            data?.let { (binding.contactsRecyclerView.adapter as ContactsAdapter).setData(data.contacts) }
        })
    }

    override fun onClick(contact: Contact) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
