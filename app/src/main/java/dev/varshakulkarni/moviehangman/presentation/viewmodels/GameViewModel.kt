/*
 * Copyright 2023 Varsha Kulkarni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.varshakulkarni.moviehangman.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.varshakulkarni.core.model.Movie
import dev.varshakulkarni.core.repository.HangmanDataSource
import dev.varshakulkarni.moviehangman.presentation.utils.GameScoreState
import dev.varshakulkarni.moviehangman.presentation.utils.contains
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
    private var gameScoreState: GameScoreState = GameScoreState.StillPlaying
    private var isExhausted = false

    // Hack to ignore initial null state: still not sure whether StateFlow is the right thing to use here!
    private var nullCount = 0

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
            gameScoreState = GameScoreState.StillPlaying
            isExhausted = false

            playWithRandomMovie()
        }
    }

    private fun playGame(movie: Movie) {
        currentMovie = movie
        hiddenWord = refactorHiddenString(movie.title)

        updateState()
    }

    private fun updateState() {
        if (isExhausted) {
            _state.update { currentState ->
                currentState.copy(
                    isExhausted = isExhausted
                )
            }
        } else {
            _state.update { currentState ->
                currentState.copy(
                    isGameOver = isGameOver,
                    movie = currentMovie,
                    hiddenWord = hiddenWord,
                    gameScore = gameScore,
                    lives = lives,
                    buttonMap = buttonMap,
                    gameScoreState = gameScoreState,
                    isExhausted = isExhausted
                )
            }
        }
    }

    private suspend fun playWithRandomMovie() {
        val movie = movieDataSource.getMovie().firstOrNull()
        if (movie == null) {
            nullCount++
            if (nullCount > 1)
                gameExhausted()
            else
                playWithRandomMovie()
        } else {
            movieDataSource.updateMovie(movie)
            playGame(movie)
        }
    }

    private fun gameExhausted() {
        isExhausted = true
        updateState()
    }

    private fun resetButtons(alphabets: List<List<String>>) {
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
            if (s == ' ' || s == ':' || s == '-' || s == '.' || s == ',' || s.toString()
                .compareTo("'") == 0
            )
                correctGuesses.add(s.toString())

            hidden += (checkIfGuessed(s.toString()))
        }
        return hidden
    }

    private fun checkIfGuessed(s: String): String {
        return when (correctGuesses.contains(s, true)) {
            true -> s
            false -> "-"
        }
    }

    fun checkForAlphabets(alphabet: String) {

        buttonMap[alphabet] = false

        if (currentMovie.title
            .contains(alphabet, true)
        ) {

            if (alphabet.isNotEmpty() && alphabet.isNotBlank()) {
                gameScore += 10
            }

            correctGuesses.add(alphabet)

            hiddenWord = refactorHiddenString(currentMovie.title)

            if (!hiddenWord.contains('-')) {
                isGameOver = true
                gameScoreState = GameScoreState.Won
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
            gameScoreState = GameScoreState.Lost
        }

        updateState()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
