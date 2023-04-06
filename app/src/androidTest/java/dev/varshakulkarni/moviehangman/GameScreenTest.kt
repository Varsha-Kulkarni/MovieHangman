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
package dev.varshakulkarni.moviehangman

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.varshakulkarni.moviehangman.presentation.screens.GameScreen
import dev.varshakulkarni.moviehangman.presentation.ui.theme.MovieHangmanTheme
import dev.varshakulkarni.moviehangman.presentation.viewmodels.GameViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class GameScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val buttonMap: HashMap<String, Boolean> = HashMap()

    @Before
    fun setup() {
        hiltTestRule.inject()

        composeTestRule.activity.setContent {
            MovieHangmanTheme {
                GameScreen(composeTestRule.activity.viewModels<GameViewModel>().value)
            }
        }
        resetButtons()
    }

    @Test
    fun gameScreen_initStateDisplayed() {
        assertStringDisplayed("------------")
        assertStringDisplayed("Score: 0")
        assertStringDisplayed("Lives: 6")
    }

    @Test
    fun gameScreen_testGameProgress() {
        clickOnKey("i")
        assertStringDisplayed("I-----------")
        assertStringDisplayed("Score: 10")

        clickOnKey("n")
        clickOnKey("t")
        assertStringDisplayed("Int---t-----")
        assertStringDisplayed("Score: 30")

        clickOnKey("e")
        clickOnKey("r")
        clickOnKey("s")
        clickOnKey("l")
        clickOnKey("a")

        assertStringDisplayed("Interstellar")
        assertStringDisplayed("Score: 80")
    }

    private fun clickOnKey(key: String) =
        composeTestRule
            .onNodeWithText(key, useUnmergedTree = true)
            .performClick()

    private fun assertStringDisplayed(string: String) {
        composeTestRule.onNodeWithText(string, ignoreCase = true).assertIsDisplayed()
    }

    private fun resetButtons() {
        val alphabets = listOf(
            listOf("a", "b", "c", "d", "e", "f"), listOf("g", "h", "i", "j", "k", "l"),
            listOf("m", "n", "o", "p", "q", "r"), listOf("s", "t", "u", "v", "w", "x"),
            listOf("y", "z", "1", "2", "3", "4"), listOf("5", "6", "7", "8", "9", "0")
        )

        for (row in alphabets) {
            for (alphabet in row) {
                buttonMap[alphabet] = true
            }
        }
    }
}
