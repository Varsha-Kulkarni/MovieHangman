package dev.varshakulkarni.moviehangman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.varshakulkarni.moviehangman.presentation.screens.GameScreen
import dev.varshakulkarni.moviehangman.presentation.ui.theme.MovieHangmanTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieHangmanTheme {
                GameScreen()
            }
        }
    }
}
