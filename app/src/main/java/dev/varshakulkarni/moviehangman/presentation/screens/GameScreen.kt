package dev.varshakulkarni.moviehangman.presentation.screens

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.varshakulkarni.moviehangman.R
import dev.varshakulkarni.moviehangman.presentation.components.HangmanParts
import dev.varshakulkarni.moviehangman.presentation.utils.GameScoreState
import dev.varshakulkarni.moviehangman.presentation.viewmodels.GameViewModel

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.padding(20.dp)) {
        HangmanContent(viewModel)
    }
}

@Composable
fun HangmanContent(
    viewModel: GameViewModel,
) {
    val state by viewModel.state.collectAsState()

    val configuration = LocalConfiguration.current

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,

        content = {
            if (state.isExhausted) {
                EndGameDialog()
            }

            if (state.isGameOver) {
                val dialogTitle = if (state.gameScoreState == GameScoreState.Won) listOf(
                    R.string.congratulations,
                    R.string.well_done
                ).random() else listOf(R.string.better_luck, R.string.keep_trying).random()
                val buttonTitle =
                    if (state.gameScoreState == GameScoreState.Won) R.string.play_again else R.string.play
                FinalScoreDialog(
                    title = state.movie?.title ?: "",
                    description = state.movie?.description ?: "",
                    score = state.gameScore,
                    dialogTitle = dialogTitle,
                    buttonTitle = buttonTitle,
                    onPlayAgain = {
                        viewModel.resetGame()
                    }
                )
            }

            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    GameStatus(score = state.gameScore, lives = state.lives)
                    GameString(state.hiddenWord)

                    InputButtonLayout(
                        buttonMap = state.buttonMap,
                        onKeyPressed = { alphabet -> viewModel.checkForAlphabets(alphabet) }
                    )
                    HangmanDrawingStatus(state.lives)
                }
            } else {
                Column {
                    GameStatus(score = state.gameScore, lives = state.lives)
                    GameString(state.hiddenWord)

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        InputButtonLayout(
                            buttonMap = state.buttonMap,
                            onKeyPressed = { alphabet -> viewModel.checkForAlphabets(alphabet) }
                        )
                        HangmanDrawingStatus(state.lives)
                    }
                }
            }
        })
}



@Composable
fun GameString(hiddenWord: String) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(text = hiddenWord, letterSpacing = 4.sp, fontSize = 24.sp, modifier = Modifier)
    }
}

@Composable
fun GameStatus(score: Int, lives: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = stringResource(R.string.score, score),
            fontSize = 12.sp,
        )
        Text(
            text = stringResource(R.string.lives, lives),
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
        )
    }
}

@Composable
fun HangmanDrawingStatus(lives: Int) {
    HangmanParts(lives = lives)
}

@Composable
fun InputButtonLayout(
    buttonMap: HashMap<String, Boolean>,
    onKeyPressed: (String) -> Unit

) {
    val alphabets = listOf(
        listOf("a", "b", "c", "d", "e", "f"), listOf("g", "h", "i", "j", "k", "l"),
        listOf("m", "n", "o", "p", "q", "r"), listOf("s", "t", "u", "v", "w", "x"),
        listOf("y", "z", "1", "2", "3", "4"), listOf("5", "6", "7", "8", "9", "0")
    )

    Column {
        for (row in alphabets) {
            Row {
                for (alphabet in row) {
                    buttonMap[alphabet]?.let {
                        Button(
                            onClick = { onKeyPressed(alphabet) },
                            enabled = it,
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = Color.White,
                                contentColor = Color.DarkGray
                            ),
                        ) {
                            Text(alphabet)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FinalScoreDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    score: Int,
    dialogTitle: Int,
    onPlayAgain: () -> Unit,
    buttonTitle: Int,
) {
    val activity = (LocalContext.current as Activity)

    val globalText = stringResource(id = R.string.movie_details, description, title)

    val start = globalText.indexOf(title)
    val spanStyles = listOf(
        AnnotatedString.Range(
            SpanStyle(fontWeight = FontWeight.Bold),
            start = start,
            end = start + title.length
        )
    )

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = stringResource(id = dialogTitle)) },
        text = {
            Column {
                Text(stringResource(R.string.you_scored, score))
                Text(stringResource(id = R.string.movie_detail))
                Text(text = AnnotatedString(text = globalText, spanStyles = spanStyles))
            }
        },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(buttonTitle))
            }
        }
    )
}

@Composable
private fun EndGameDialog(
    modifier: Modifier = Modifier
) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(stringResource(R.string.congratulations)) },
        text = { Text(stringResource(R.string.end_game)) },
        modifier = modifier,
        confirmButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = stringResource(R.string.exit))
            }
        }
    )
}