package ealanhill.me.contacts

/**
 * Interface to allow the contacts recycler view to work smoothly (ie don't need to do some weird
 * stuff to combine two lists in an adapter
 */
interface ContactsInterface {

    val type: Type

    enum class Type {
        CONTACT,
        HEADER
    }
}