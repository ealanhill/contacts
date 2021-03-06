package ealanhill.me.contacts.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import ealanhill.me.contacts.ContactsStore
import ealanhill.me.contacts.GlideApp
import ealanhill.me.contacts.R
import ealanhill.me.contacts.databinding.FragmentContactBinding
import ealanhill.me.contacts.databinding.LayoutContactInfoBinding
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        val item: MenuItem = menu.findItem(R.id.menuFavoriteItem)
        handleFavoriteSelected(item, store.state.contactDetail.isFavorite)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menuFavoriteItem -> {
                val isFavorite = !store.state.contactDetail.isFavorite
                handleFavoriteSelected(item, isFavorite)
                store.dispatch(FavoriteAction(isFavorite))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun handleFavoriteSelected(item: MenuItem, isFavorite: Boolean) {
        item.isChecked = isFavorite
        val drawable = if (isFavorite) R.drawable.ic_favorite_true else R.drawable.ic_favorite_false
        item.icon = ContextCompat.getDrawable(activity, drawable)
    }

    private fun bindContactDetails(binding: FragmentContactBinding, contact: ContactDetail) {
        binding.apply {
            textContactName.text = contact.contactName
            textContactBusiness.text = contact.company
            GlideApp.with(activity)
                    .load(contact.image)
                    .placeholder(R.drawable.ic_user_large)
                    .error(R.drawable.ic_user_large)
                    .fallback(R.drawable.ic_user_large)
                    .into(imageUserLarge)
            contactRecyclerView.adapter = ContactInfoAdapter(contact.infoEntry)
            contactRecyclerView.layoutManager = LinearLayoutManager(activity)
        }
    }

    class ContactInfoAdapter(private var infoEntry: List<ContactInfoEntry>): RecyclerView.Adapter<ContactInfoEntryViewHolder>() {
        override fun getItemCount(): Int = infoEntry.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactInfoEntryViewHolder {
            return ContactInfoEntryViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_contact_info, parent, false) as ConstraintLayout)
        }

        override fun onBindViewHolder(holder: ContactInfoEntryViewHolder, position: Int) {
            holder.bind(infoEntry[position])
        }

    }

    class ContactInfoEntryViewHolder(itemView: ConstraintLayout): RecyclerView.ViewHolder(itemView) {

        private val binding: LayoutContactInfoBinding = DataBindingUtil.bind(itemView)

        fun bind(contactInfoEntry: ContactInfoEntry) {
            binding.textInfoType.text = contactInfoEntry.infoType
            binding.textInfo.text = contactInfoEntry.info
            binding.textExtraInfo.text = contactInfoEntry.extra
        }
    }
}
