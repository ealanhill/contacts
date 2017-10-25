package ealanhill.me.contacts.detail

import ealanhill.me.contacts.Action

/**
 * Action to indicate the selected contact currently selected has been favorited/unfavorited
 */
data class FavoriteAction(val isFavorite: Boolean): Action