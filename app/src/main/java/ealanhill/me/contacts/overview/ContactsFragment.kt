package ealanhill.me.contacts.overview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ealanhill.me.contacts.App
import ealanhill.me.contacts.ContactsStore
import ealanhill.me.contacts.MainActivity
import ealanhill.me.contacts.R
import ealanhill.me.contacts.databinding.FragmentContactsBinding
import ealanhill.me.contacts.detail.ContactDetailAction
import ealanhill.me.contacts.detail.ContactDetailFragment
import ealanhill.me.contacts.network.models.Contact
import javax.inject.Inject

class ContactsFragment : Fragment(), ContactsAdapter.ContactsOnClickListener {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var store: ContactsStore

    @Inject
    lateinit var actionCreator: ContactsActionCreator

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contactsViewModel = ViewModelProviders.of(activity)
                .get(ContactsViewModel::class.java)
        store = contactsViewModel.store
        App.COMPONENT.inject(this)
        store.dispatch(actionCreator.retrieveContacts())
        binding = DataBindingUtil.inflate<FragmentContactsBinding>(inflater, R.layout.fragment_contacts, container, false)
                .apply {
                    contactsRecyclerView.layoutManager = LinearLayoutManager(context)
                    contactsRecyclerView.adapter = ContactsAdapter(store.state.contacts, this@ContactsFragment)
                }

        contactsViewModel.state.observe(this, Observer { data ->
            data?.let { (binding.contactsRecyclerView.adapter as ContactsAdapter).setData(data.contacts) }
        })
        return binding.root
    }

    override fun onClick(contact: Contact) {
        store.dispatch(ContactDetailAction(contact))
        (activity as MainActivity).swapFragments(ContactDetailFragment())
    }
}
