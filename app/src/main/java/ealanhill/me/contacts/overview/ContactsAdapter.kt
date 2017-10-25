package ealanhill.me.contacts.overview

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import ealanhill.me.contacts.App
import ealanhill.me.contacts.ContactsInterface
import ealanhill.me.contacts.GlideApp
import ealanhill.me.contacts.R
import ealanhill.me.contacts.databinding.LayoutContactBinding
import ealanhill.me.contacts.databinding.LayoutDividerBinding
import ealanhill.me.contacts.network.models.Contact
import javax.inject.Inject

class ContactsAdapter(var contacts: List<ContactsInterface>, private val onClickListener: ContactsOnClickListener):
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        App.COMPONENT.inject(this)
    }

    @Inject
    lateinit var context: Context

    interface ContactsOnClickListener {
        fun onClick(contact: Contact)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder::class) {
            ContactViewHolder::class -> (holder as ContactViewHolder).bind(
                    contact = contacts[position] as Contact,
                    context = context)
            HeaderViewHolder::class -> {
                val string = context.getString((contacts[position] as ContactsHeader).text)
                (holder as HeaderViewHolder).bind(string)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            R.layout.layout_divider -> HeaderViewHolder(
                    inflater.inflate(R.layout.layout_divider, parent, false) as LinearLayout)
            R.layout.layout_contact -> ContactViewHolder(
                    inflater.inflate(R.layout.layout_contact, parent, false) as ConstraintLayout,
                    onClickListener = onClickListener)
            else -> throw IllegalArgumentException("Unknown view type " + viewType)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when(contacts[position].type) {
            ContactsInterface.Type.HEADER -> R.layout.layout_divider
            ContactsInterface.Type.CONTACT -> R.layout.layout_contact
        }

    override fun getItemCount(): Int = contacts.size

    fun setData(newContacts: List<ContactsInterface>) {
        if (newContacts != contacts) {
            contacts = newContacts
            notifyDataSetChanged()
        }
    }

    class ContactViewHolder(itemView: ConstraintLayout, val onClickListener: ContactsOnClickListener):
            RecyclerView.ViewHolder(itemView) {

        private val binding: LayoutContactBinding = DataBindingUtil.bind(itemView)

        fun bind(contact: Contact, context: Context) {
            binding.apply {
                GlideApp.with(context)
                        .load(contact.smallImageUrl)
                        .placeholder(R.drawable.ic_user_small)
                        .fallback(R.drawable.ic_user_small)
                        .error(R.drawable.ic_user_small)
                        .into(imageUserSmall)
                imageFavorite.visibility = if (contact.isFavorite) View.VISIBLE else View.INVISIBLE
                textContactName.text = contact.name
                textContactBusiness.text = contact.companyName
            }
            binding.root.setOnClickListener { _ -> onClickListener.onClick(contact) }
        }
    }

    class HeaderViewHolder(itemView: LinearLayout): RecyclerView.ViewHolder(itemView) {

        private val binding: LayoutDividerBinding = DataBindingUtil.bind(itemView)

        fun bind(text: String) {
            binding.contactsTypeHeader.text = text
        }
    }
}