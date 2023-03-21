package dev.varshakulkarni.moviehangman.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.varshakulkarni.moviehangman.presentation.screens.GameScreen
import dev.varshakulkarni.moviehangman.presentation.screens.MovieInfoScreen

@Composable
fun HangmanNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HangmanScreens.GameScreen.name
    ) {

        composable(HangmanScreens.GameScreen.name) {
            GameScreen()
        }

        composable(
            HangmanScreens.MovieInfoScreen.name + "/{title}",
            arguments = listOf(navArgument(name = "title") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")

            MovieInfoScreen(title)
        }
    }
}