package ealanhill.me.contacts.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ealanhill.me.contacts.ContactsStore
import ealanhill.me.contacts.GlideApp
import ealanhill.me.contacts.R
import ealanhill.me.contacts.databinding.FragmentContactBinding
import ealanhill.me.contacts.overview.ContactsViewModel

class ContactDetailFragment : Fragment() {

    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var store: ContactsStore

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contactsViewModel = ViewModelProviders.of(activity)
                .get(ContactsViewModel::class.java)
        store = contactsViewModel.store
        val binding = DataBindingUtil.inflate<FragmentContactBinding>(inflater, R.layout.fragment_contact, container, false)
        bindContactDetails(binding, store.state.contactDetail)
        contactsViewModel.state.observe(this, Observer { data ->
            data?.let { bindContactDetails(binding, data.contactDetail) }
        })
        return binding.root
    }

    private fun bindContactDetails(binding: FragmentContactBinding, contact: ContactDetail) {
        binding.apply {
            textContactName.text = contact.contactName
            textContactBusiness.text = contact.company
            GlideApp.with(this@ContactDetailFragment)
                    .load(contact.image)
                    .placeholder(R.drawable.ic_user_large)
                    .error(R.drawable.ic_user_large)
                    .fallback(R.drawable.ic_user_large)
        }
    }
}
