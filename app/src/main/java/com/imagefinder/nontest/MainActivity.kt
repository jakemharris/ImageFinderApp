package com.imagefinder.nontest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imagefinder.nontest.ui.Routes
import com.imagefinder.nontest.ui.detailScreen.DetailScreen
import com.imagefinder.nontest.ui.searchScreen.SearchScreen
import com.imagefinder.nontest.ui.theme.ImageFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageFinderTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.SEARCH_SCREEN) {
                    composable(Routes.SEARCH_SCREEN) {
                        SearchScreen(
                            navigation = navController,
                        )
                    }
                    composable(Routes.DETAIL_SCREEN) {
                        DetailScreen(
                            navigation = navController
                        )
                    }
                }
            }
        }
    }
}
