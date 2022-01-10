package com.github.ephelsa.okmoviesplace.remote.json

import com.github.ephelsa.okmoviesplace.model.ModelMapper
import com.github.ephelsa.okmoviesplace.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieJson(
    val id: Int,
    val adult: Boolean,
    val title: String,
    @SerialName("vote_average")
    val votesAverage: Double,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("poster_path")
    val posterImagePath: String,
) : ModelMapper<Movie> {

    override fun asModel(): Movie {
        return Movie(
            id = id,
            imagePath = posterImagePath,
            title = title,
            isAdult = adult,
            votesAverage = votesAverage,
            genres = emptyList()
        )
    }
}