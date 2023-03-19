package dev.varshakulkarni.moviehangman.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.varshakulkarni.moviehangman.presentation.screens.HangmanGameScreen
import dev.varshakulkarni.moviehangman.presentation.screens.MovieInfo

@Composable
fun HangmanNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HangmanScreens.GameScreen.name
    ) {

        composable(HangmanScreens.GameScreen.name) {
            HangmanGameScreen()
        }

        composable(
            HangmanScreens.MovieInfoScreen.name + "/{title}",
            arguments = listOf(navArgument(name = "title") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")

            MovieInfo(title)
        }
    }
}