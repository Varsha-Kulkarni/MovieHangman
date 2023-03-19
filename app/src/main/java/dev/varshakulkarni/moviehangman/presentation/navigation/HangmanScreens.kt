package dev.varshakulkarni.moviehangman.presentation.navigation

enum class HangmanScreens {
    GameScreen,
    MovieInfoScreen;

    companion object {
        fun fromRoute(route: String?): HangmanScreens =
            when (route?.substringBefore("/")) {
                GameScreen.name -> GameScreen
                MovieInfoScreen.name -> MovieInfoScreen
                null -> GameScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}