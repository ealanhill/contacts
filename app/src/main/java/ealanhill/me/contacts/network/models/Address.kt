package ealanhill.me.contacts.network.models

data class Address(val street: String = "",
                   val city: String = "",
                   val state: String = "",
                   val country: String = "",
                   val zip: Int = -1)