package ealanhill.me.contacts.network.models

data class Phone(val work: String = "",
                 val home: String = "",
                 val mobile: String = "") {
    enum class Type(val string: String) {
        WORK("Work"),
        HOME("Home"),
        MOBILE("Mobile")
    }
}