package dev.varshakulkarni.moviehangman.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke


private const val SCAFFOLD_HEIGHT = 360f
private const val BEAM_LENGTH = 144f
private const val ROPE_LENGTH = 18f
private const val HEAD_RADIUS = 36f
private const val BODY_LENGTH = 144f
private const val ARM_OFFSET_FROM_HEAD = 28f
private const val ARM_LENGTH = 72f
private const val HIP_WIDTH = 36f
private const val ARM_ANGLE = 28f
private const val LEG_ANGLE = 28f
private const val HANGMAN_WIDTH_OFFSET = 2.5f
private const val HANGMAN_HEIGHT_OFFSET = 3
private const val ANIMATION_DURATION = 400

@Composable
fun HangmanParts(numberOfMisses: Int) {

    val animatableHead = remember() { Animatable(0f) }
    val animatableBody = remember() { Animatable(0f) }
    val animatableLeftArm = remember() { Animatable(0f) }
    val animatableRightArm = remember() { Animatable(0f) }
    val animatableLeftLeg = remember() { Animatable(0f) }
    val animatableRightLeg = remember() { Animatable(0f) }

    if (numberOfMisses == 5)
        LaunchedEffect(animatableHead) {
            animatableHead.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (numberOfMisses == 4)
        LaunchedEffect(animatableBody) {
            animatableBody.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (numberOfMisses == 3)
        LaunchedEffect(animatableLeftArm) {
            animatableLeftArm.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (numberOfMisses == 2)
        LaunchedEffect(animatableRightArm) {
            animatableRightArm.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (numberOfMisses == 1)
        LaunchedEffect(animatableLeftLeg) {
            animatableLeftLeg.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (numberOfMisses == 0)
        LaunchedEffect(animatableRightLeg) {
            animatableRightLeg.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Canvas(modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawRoundRect(
                    color = Color.Black,
                    topLeft = Offset(0f, 36f),
                    size = Size(size.width, size.height - 36f),
                    cornerRadius = CornerRadius(2f, 2f),
                    style = Stroke(2f)
                )
                drawLine(
                    Color.Black,
                    Offset(
                        size.width / HANGMAN_WIDTH_OFFSET,
                        (size.height / HANGMAN_HEIGHT_OFFSET + SCAFFOLD_HEIGHT)
                    ),
                    Offset(
                        size.width / HANGMAN_WIDTH_OFFSET,
                        size.height / HANGMAN_HEIGHT_OFFSET
                    ),
                    strokeWidth = 2f,
                )
                drawLine(
                    Color.Black,
                    Offset(
                        size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                        size.height / HANGMAN_HEIGHT_OFFSET
                    ),
                    Offset(
                        size.width / HANGMAN_WIDTH_OFFSET,
                        size.height / HANGMAN_HEIGHT_OFFSET
                    ),
                    strokeWidth = 2f
                )
                drawLine(
                    Color.Black,
                    Offset(
                        size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                        size.height / HANGMAN_HEIGHT_OFFSET
                    ),
                    Offset(
                        size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                        size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH
                    ),
                    strokeWidth = 2f
                )
                if (numberOfMisses != 6) {
                    drawArc(
                        color = Color.Black,
                        startAngle = 0f,
                        sweepAngle = 360f * animatableHead.value,
                        false,
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - HEAD_RADIUS,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH
                        ),
                        Size(
                            HEAD_RADIUS * 2,
                            HEAD_RADIUS * 2
                        ),
                        style = Stroke(2f)
                    )

                    drawLine(
                        Color.Black,
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2
                        ),
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            (size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH * animatableBody.value)
                        ),


                        strokeWidth = 2f,
                    )

                    drawLine(
                        Color.Black,
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD
                        ),
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH + ARM_LENGTH * animatableRightArm.value,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + ARM_ANGLE * animatableRightArm.value
                        ),
                        strokeWidth = 2f
                    )

                    drawLine(
                        Color.Black,
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD
                        ),
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - ARM_LENGTH * animatableLeftArm.value,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + ARM_ANGLE * animatableLeftArm.value
                        ),
                        strokeWidth = 2f
                    )

                    drawLine(
                        Color.Black,
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH
                        ),
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH + HIP_WIDTH * animatableRightLeg.value,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_ANGLE * animatableRightLeg.value
                        ),
                        strokeWidth = 2f
                    )

                    drawLine(
                        Color.Black,
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH
                        ),
                        Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - HIP_WIDTH * animatableLeftLeg.value,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_ANGLE * animatableLeftLeg.value
                        ),
                        strokeWidth = 2f
                    )
                }
            })
    }
}