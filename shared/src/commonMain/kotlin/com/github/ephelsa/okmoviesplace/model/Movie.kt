package com.github.ephelsa.okmoviesplace.model

data class Movie(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val isAdult: Boolean,
    val votesAverage: Double,
    val genres: List<Genre>
)
