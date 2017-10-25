package ealanhill.me.contacts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import ealanhill.me.contacts.overview.ContactsFragment

class MainActivity: AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swapFragments(ContactsFragment())
        supportFragmentManager.addOnBackStackChangedListener(this)
        shouldDisplayHomeAsUp()
    }

    fun swapFragments(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
        if (addToBackStack) {
            transaction.addToBackStack("contacts")
        }
        transaction.commit()
    }

    override fun onBackStackChanged() {
        shouldDisplayHomeAsUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    private fun shouldDisplayHomeAsUp() {
        supportActionBar?.setDisplayHomeAsUpEnabled(
                supportFragmentManager.backStackEntryCount > 0
        )
    }
}