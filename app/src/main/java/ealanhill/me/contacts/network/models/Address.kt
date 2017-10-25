package ealanhill.me.contacts.network.models

data class Address(val street: String = "",
                   val city: String = "",
                   val state: String = "",
                   val country: String = "",
                   val zip: Int = -1) {
    fun format(): String =
        if (zip == -1) {
            String.format("%s\n%s, %s, %s", street, city, state, country)
        } else {
            String.format("%s\n%s, %s %d, %s", street, city, state, zip, country)
        }
}