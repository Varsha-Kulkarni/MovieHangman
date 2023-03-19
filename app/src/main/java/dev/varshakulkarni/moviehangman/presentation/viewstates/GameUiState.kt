package dev.varshakulkarni.moviehangman.presentation.viewstates

import dev.varshakulkarni.core.model.Movie

data class GameUiState(
    val isLoading: Boolean = false,
    val lives: Int = 6,
    val gameScore: Int = 0,
    val movie: Movie? = null,
    val hiddenWord: String = "",
    val isGameOver: Boolean = false
)