package com.github.ephelsa.okmoviesplace.android.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.repository.GenreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val genreRepository: GenreRepository
) : ViewModel() {

    private val movieGenres = MutableStateFlow<List<Genre>?>(null)
    val onMovieGenres: StateFlow<List<Genre>?>
        get() = movieGenres.asStateFlow()

    private val trendingMovies = MutableStateFlow<List<Movie>?>(null)
    val onTrendingMovies: StateFlow<List<Movie>?>
        get() = trendingMovies.asStateFlow()

    fun movieGenres() {
        viewModelScope.launch {
            movieGenres.emit(genreRepository.allMovieGenres())
        }
    }

    fun trendingMovies() {
        viewModelScope.launch {
            trendingMovies.emit(null)
        }
    }
}