package dev.varshakulkarni.moviehangman.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.varshakulkarni.core.model.Movie
import dev.varshakulkarni.core.repository.HangmanDataSource
import dev.varshakulkarni.moviehangman.presentation.viewstates.GameUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val movieDataSource: HangmanDataSource) :
    ViewModel() {

    private var job: Job? = null

    private lateinit var currentMovie: Movie

    private val _state = MutableStateFlow(GameUiState())
    val state: StateFlow<GameUiState> = _state.asStateFlow()

    private val correctGuesses = mutableListOf<String>()
    private var gameScore = 0
    private var hiddenWord = ""
    private var lives = 6
    private var isGameOver: Boolean = false
    private val buttonMap: HashMap<String, Boolean> = HashMap()

    init {
        resetGame()
    }

    fun resetGame() {
        val alphabets = listOf(
            listOf("a", "b", "c", "d", "e", "f"), listOf("g", "h", "i", "j", "k", "l"),
            listOf("m", "n", "o", "p", "q", "r"), listOf("s", "t", "u", "v", "w", "x"),
            listOf("y", "z", "1", "2", "3", "4"), listOf("5", "6", "7", "8", "9", "0")
        )

        resetButtons(alphabets)

        job = viewModelScope.launch {
            correctGuesses.clear()
            gameScore = 0
            lives = 6
            isGameOver = false
            hiddenWord = ""

            val movie = movieDataSource.getMovie().firstOrNull()

            _state.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }

            if (movie != null) {
                currentMovie = movie
                hiddenWord = refactorHiddenString(movie.title)

                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        isGameOver = isGameOver,
                        movie = currentMovie,
                        hiddenWord = hiddenWord,
                        gameScore = gameScore,
                        lives = lives,
                        buttonMap = buttonMap
                    )
                }
            } else {
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        isExhausted = true
                    )
                }
            }
        }
    }

    fun resetButtons(alphabets: List<List<String>>) {
        for (row in alphabets) {
            for (alphabet in row) {
                buttonMap[alphabet] = true
            }
        }
    }

    private fun refactorHiddenString(
        gameString: String,
    ): String {
        var hidden = ""
        gameString.forEach { s ->
            if (s == ' ' || s == ':' || s == '-' || s == '.' || s.toString().compareTo("'") == 0)
                correctGuesses.add(s.toString())

            hidden += (checkIfGuessed(s.toString()))
        }
        return hidden
    }

    private fun checkIfGuessed(s: String): String {
        return when (correctGuesses.contains(s)) {
            true -> s
            false -> "-"
        }
    }

    fun checkForAlphabets(alphabet: String) {

        buttonMap[alphabet] = false

        if (currentMovie.title.lowercase()
                .contains(alphabet)
        ) {

            if (alphabet.isNotEmpty() && alphabet.isNotBlank()) {
                gameScore += 10
            }

            correctGuesses.add(alphabet)

            hiddenWord = refactorHiddenString(currentMovie.title.lowercase())

            if (!hiddenWord.contains('-')) {
                isGameOver = true
                viewModelScope.launch {
                    movieDataSource.updateMovie(currentMovie)
                }
            }
        } else {
            if (lives >= 1) {
                lives--
                if (gameScore != 0)
                    gameScore -= 5
            }
        }
        if (lives == 0) {
            isGameOver = true
        }

        _state.update { currentState ->
            currentState.copy(
                isLoading = false,
                movie = currentMovie,
                gameScore = gameScore,
                isGameOver = isGameOver,
                lives = lives,
                hiddenWord = hiddenWord,
                buttonMap = buttonMap
            )

        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}