package ealanhill.me.contacts

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import ealanhill.me.contacts.databinding.ActivityContactsBinding
import ealanhill.me.contacts.databinding.LayoutContactBinding
import ealanhill.me.contacts.databinding.LayoutDividerBinding
import ealanhill.me.contacts.network.models.Contact
import okhttp3.HttpUrl
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
