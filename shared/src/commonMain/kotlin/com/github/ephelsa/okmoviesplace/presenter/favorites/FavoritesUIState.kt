package com.github.ephelsa.okmoviesplace.presenter.favorites

import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.presenter.UIState

sealed class FavoritesUIState : UIState {
    data class Ready(
        val movies: List<Movie>,
    ) : FavoritesUIState()

    object Empty : FavoritesUIState()
    object Error : FavoritesUIState()
    object Loading : FavoritesUIState()
}