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
package dev.varshakulkarni.moviehangman.viewstates

import dev.varshakulkarni.core.model.Movie
import dev.varshakulkarni.moviehangman.utils.GameScoreState

data class GameUiState(
    val lives: Int = 6,
    val currentScore: Int = 0,
    val highScore: Int = 0,
    val movie: Movie? = null,
    val hiddenWord: String = "",
    val isGameOver: Boolean = false,
    val buttonMap: HashMap<String, Boolean> = HashMap(),
    val isExhausted: Boolean = false,
    val highestWinningStreak: Int = 0,
    val currentWinningStreak: Int = 0,
    val previous: Int = 0,
    val gameScoreState: GameScoreState = GameScoreState.StillPlaying,
)
