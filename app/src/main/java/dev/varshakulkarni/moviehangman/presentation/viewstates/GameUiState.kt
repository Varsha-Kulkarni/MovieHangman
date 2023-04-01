package dev.varshakulkarni.moviehangman.presentation.viewstates

import dev.varshakulkarni.core.model.Movie
import dev.varshakulkarni.moviehangman.presentation.utils.GameScoreState

data class GameUiState(
    val lives: Int = 6,
    val gameScore: Int = 0,
    val movie: Movie? = null,
    val hiddenWord: String = "",
    val isGameOver: Boolean = false,
    val buttonMap: HashMap<String, Boolean> = HashMap(),
    val isExhausted: Boolean = false,
    val gameScoreState: GameScoreState = GameScoreState.StillPlaying,
)