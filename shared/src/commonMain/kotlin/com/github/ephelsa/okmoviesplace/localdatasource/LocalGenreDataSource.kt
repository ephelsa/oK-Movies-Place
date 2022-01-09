package com.github.ephelsa.okmoviesplace.localdatasource

import com.github.ephelsa.okmoviesplace.model.Genre

internal interface LocalGenreDataSource {
    suspend fun storeMovieList(genres: List<Genre>)

    suspend fun isMovieListEmpty(): Boolean

    suspend fun movieById(id: Int): Genre?
}