package ealanhill.me.contacts

data class ContactsHolder(val contact: ContactsInterface, val type: Type) {
    enum class Type {
        CONTACT,
        HEADER
    }
}