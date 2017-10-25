# Contacts App

An app to retrieve contacts from an external source and display them to the user.
The app shows all the contacts as separated into two sections: "Favorite
Contacts" and "Other Contacts". When the user taps on a contact, a detail screen
of the contact is shown to the user. The user is able to favorite and un-favorite
a contact on the detail screen.

### Architecture

This app uses the Redux architecture, an architecture that emphasizes
unidirectional data flow. The app's state is represented by a `State`, in this
case `ContactsState`. The state can only be mutated by functions called
`Reducers`, which are designed to be pure functions. In order for an
`Activity`/`Fragment` to mutate a state, they issue `Actions`. The action issued is matched
to a reducer(s), which performs mutates the state.

For more information on Redux you can see:

 * [Redux](http://redux.js.org/)
 * [Android.apply{ Redux }](https://blog.shazam.com/android-apply-redux-2ad0f7355e0)
 * [Writing a Todo app with Redux on Android](https://medium.com/@trikita/writing-a-todo-app-with-redux-on-android-5de31cfbdb4f)

### External Libraries

The external libraries used in this app:

 * [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
 * [Dagger 2](https://google.github.io/dagger/)
 * [Android Data Binding Library](https://developer.android.com/topic/libraries/data-binding/index.html)
 * [Retrofit](http://square.github.io/retrofit/)
 * [gson](https://github.com/google/gson)
 * [Redux](https://github.com/evant/redux)
 * [Glide](http://bumptech.github.io/glide/)
