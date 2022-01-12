package com.github.ephelsa.okmoviesplace.android.ui.screen.favorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeature
import com.github.ephelsa.okmoviesplace.android.ui.component.DiscoveryFeatureTab
import com.github.ephelsa.okmoviesplace.android.ui.theme.OKMoviesPlaceTheme
import com.github.ephelsa.okmoviesplace.presenter.Navigation

class FavoritesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OKMoviesPlaceTheme {
                DiscoveryFeature(Navigation(this), DiscoveryFeatureTab.Favorites) {
                    Text(text = "Hello from Favorites! :D")
                }
            }
        }
    }
}