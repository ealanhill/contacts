package ealanhill.me.contacts.firstPage

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import ealanhill.me.contacts.*
import ealanhill.me.contacts.databinding.ActivityContactsBinding
import ealanhill.me.contacts.databinding.LayoutContactBinding
import ealanhill.me.contacts.databinding.LayoutDividerBinding
import ealanhill.me.contacts.network.models.Contact
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

    class ContactsAdapter(val contacts: List<ContactsInterface>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        init {
            App.COMPONENT.inject(this)
        }

        @Inject
        lateinit var context: Context

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
                        inflater.inflate(R.layout.layout_contact, parent, false) as ConstraintLayout)
                else -> throw IllegalArgumentException("Unknown view type " + viewType)
            }
        }

        override fun getItemViewType(position: Int): Int =
            when(contacts[position].type) {
                ContactsInterface.Type.HEADER -> R.layout.layout_divider
                ContactsInterface.Type.CONTACT -> R.layout.layout_contact
            }

        override fun getItemCount(): Int = contacts.size

    }

    class ContactViewHolder(itemView: ConstraintLayout): RecyclerView.ViewHolder(itemView) {

        val binding: LayoutContactBinding = DataBindingUtil.bind(itemView)

        fun bind(contact: Contact, context: Context) {
            binding.apply {
                GlideApp.with(context)
                        .load(contact.smallImageUrl)
                        .placeholder(R.drawable.ic_user_small)
                        .fallback(R.drawable.ic_user_small)
                        .error(R.drawable.ic_user_small)
                        .into(imageUserSmall)
                imageFavorite.visibility = if (contact.isFavorite) View.VISIBLE else View.INVISIBLE
                textUserName.text = contact.name
                textUserBusiness.text = contact.companyName
            }
        }
    }

    class HeaderViewHolder(itemView: LinearLayout): RecyclerView.ViewHolder(itemView) {

        val binding: LayoutDividerBinding = DataBindingUtil.bind(itemView)

        fun bind(text: String) {
            binding.contactsTypeHeader.text = text
        }
    }
}
