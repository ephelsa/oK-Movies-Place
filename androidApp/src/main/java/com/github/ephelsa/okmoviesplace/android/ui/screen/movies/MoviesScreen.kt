package com.github.ephelsa.okmoviesplace.android.ui.screen.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ephelsa.okmoviesplace.android.R
import com.github.ephelsa.okmoviesplace.android.ui.component.ComingSoonCard
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeature
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeatureTab
import com.github.ephelsa.okmoviesplace.android.ui.component.MovieCard
import com.github.ephelsa.okmoviesplace.android.ui.component.PillButtonRowList
import com.github.ephelsa.okmoviesplace.android.ui.component.PillTextButton
import com.github.ephelsa.okmoviesplace.android.ui.theme.Spaces
import com.github.ephelsa.okmoviesplace.model.Genre
import com.github.ephelsa.okmoviesplace.model.Movie
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUIState
import com.github.ephelsa.okmoviesplace.presenter.movies.MoviesUserActionManager

@ExperimentalMaterialApi
@Composable
fun MoviesScreen(
    actionManager: MoviesUserActionManager,
) {
    val moviesState: MoviesUIState? by actionManager.onState.collectAsState()

    DiscoveryFeature(actionManager.navigation, DiscoveryFeatureTab.Movies) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Spaces.MediumHigh)
        ) {
            when (moviesState) {
                MoviesUIState.Error -> TODO()
                MoviesUIState.FirstIn -> TODO()
                MoviesUIState.Loading, null -> {
                    CircularProgressIndicator()
                }
                is MoviesUIState.Ready -> {
                    ComingSoonSection((moviesState as MoviesUIState.Ready).comingSoonMovies)
                    GenresSection((moviesState as MoviesUIState.Ready).movieGenres)
                    TrendingNowSection((moviesState as MoviesUIState.Ready).trendingNowMovies)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ComingSoonSection(
    upcomingMovies: List<Movie>,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardSize = screenWidth - (Spaces.Medium * 2)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spaces.Medium),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {

        Text(
            text = stringResource(R.string.label_comingSoon),
            style = MaterialTheme.typography.h1,
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spaces.Medium)
        ) {
            items(upcomingMovies) {
                ComingSoonCard(
                    modifier = Modifier.width(cardSize),
                    title = it.title,
                    imagePath = it.imagePath,
                    onClick = {
                        // TODO
                    },
                    onPlay = {
                        // TODO
                    }
                )
            }
        }
    }
}

@Composable
fun GenresSection(
    movieGenres: List<Genre>,
) {
    PillButtonRowList(
        modifier = Modifier
            .padding(horizontal = Spaces.Medium)
    ) {
        items(movieGenres) {
            PillTextButton(text = it.name)
        }
    }
}

@Composable
fun TrendingNowSection(
    trendingMovies: List<Movie>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Spaces.Medium)
    ) {
        Text(
            text = stringResource(R.string.label_trendingNow),
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(horizontal = Spaces.Medium)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(Spaces.Medium)
        ) {
            items(trendingMovies) {
                MovieCard(
                    movie = it,
                    showAll = true
                ) {
                    // TODO
                }
            }
        }
    }
}