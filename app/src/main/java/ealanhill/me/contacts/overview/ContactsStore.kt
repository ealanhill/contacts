package ealanhill.me.contacts.overview

import ealanhill.me.contacts.Action
import me.tatarka.redux.Dispatcher
import me.tatarka.redux.SimpleStore
import me.tatarka.redux.Thunk
import me.tatarka.redux.ThunkDispatcher

class ContactsStore : SimpleStore<ContactsState>(ContactsState()) {
    val dispatcher: Dispatcher<Action, Action> = Dispatcher.forStore(this, ContactsReducers.reducers())
    val thunkDispatcher: Dispatcher<Thunk<Action, Action>, Void> = ThunkDispatcher(dispatcher)

    fun dispatch(action: Action): Action = dispatcher.dispatch(action)

    fun dispatch(thunk: Thunk<Action, Action>) = thunkDispatcher.dispatch(thunk)

}