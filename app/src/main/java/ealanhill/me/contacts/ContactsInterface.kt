package ealanhill.me.contacts

interface ContactsInterface {

    val type: Type

    enum class Type {
        CONTACT,
        HEADER
    }
}