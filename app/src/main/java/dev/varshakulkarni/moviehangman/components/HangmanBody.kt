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
package dev.varshakulkarni.moviehangman.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

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
private const val STROKE_WIDTH = 8f

@SuppressLint("UnrememberedAnimatable")
@Composable
fun HangmanBody(lives: Int, bodyColor: Color = MaterialTheme.colors.onBackground) {

    /* Lint Error:Creating an Animatable during composition without using `remember`
    * This is required because the Hangman body has to redraw with every change in Game Lives
    * If it is cached, then it will not redraw during new Game
    * Hack for clearing the canvas! */

    val animatableHead = Animatable(0f)
    val animatableBody = Animatable(0f)
    val animatableLeftArm = Animatable(0f)
    val animatableRightArm = Animatable(0f)
    val animatableLeftLeg = Animatable(0f)
    val animatableRightLeg = Animatable(0f)

    if (lives == 5)
        LaunchedEffect(animatableHead) {
            animatableHead.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (lives == 4)
        LaunchedEffect(animatableBody) {
            animatableBody.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (lives == 3)
        LaunchedEffect(animatableLeftArm) {
            animatableLeftArm.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (lives == 2)
        LaunchedEffect(animatableRightArm) {
            animatableRightArm.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (lives == 1)
        LaunchedEffect(animatableLeftLeg) {
            animatableLeftLeg.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    if (lives == 0)
        LaunchedEffect(animatableRightLeg) {
            animatableRightLeg.animateTo(
                1f,
                animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = LinearEasing)
            )
        }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "HangmanBody" },
            onDraw = {
                drawRoundRect(
                    bodyColor,
                    topLeft = Offset(0f, 36f),
                    size = Size(size.width, size.height - 36f),
                    cornerRadius = CornerRadius(2f, 2f),
                    style = Stroke(2f)
                )
                drawLine(
                    bodyColor,
                    start = Offset(
                        size.width / HANGMAN_WIDTH_OFFSET,
                        (size.height / HANGMAN_HEIGHT_OFFSET + SCAFFOLD_HEIGHT)
                    ),
                    end = Offset(
                        size.width / HANGMAN_WIDTH_OFFSET,
                        size.height / HANGMAN_HEIGHT_OFFSET
                    ),
                    STROKE_WIDTH,
                )
                drawLine(
                    bodyColor,
                    start = Offset(
                        size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                        size.height / HANGMAN_HEIGHT_OFFSET
                    ),
                    end = Offset(
                        size.width / HANGMAN_WIDTH_OFFSET,
                        size.height / HANGMAN_HEIGHT_OFFSET
                    ),
                    STROKE_WIDTH
                )
                drawLine(
                    bodyColor,
                    start = Offset(
                        size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                        size.height / HANGMAN_HEIGHT_OFFSET
                    ),
                    end = Offset(
                        size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                        size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH
                    ),
                    STROKE_WIDTH
                )
                if (lives != 6) {
                    drawArc(
                        bodyColor,
                        startAngle = 0f,
                        sweepAngle = 360f * animatableHead.value,
                        false,
                        topLeft = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - HEAD_RADIUS,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH
                        ),
                        size = Size(
                            HEAD_RADIUS * 2,
                            HEAD_RADIUS * 2
                        ),
                        style = Stroke(STROKE_WIDTH)
                    )
                    if (animatableHead.value == 0f && lives <= 4) {
                        drawArc(
                            bodyColor,
                            startAngle = 0f,
                            sweepAngle = 360f,
                            false,
                            topLeft = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - HEAD_RADIUS,
                                size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH
                            ),
                            size = Size(
                                HEAD_RADIUS * 2,
                                HEAD_RADIUS * 2
                            ),
                            style = Stroke(STROKE_WIDTH)
                        )
                    }

                    drawLine(
                        bodyColor,
                        start = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2
                        ),
                        end = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            (size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH * animatableBody.value)
                        ),
                        STROKE_WIDTH,
                    )

                    if (animatableBody.value == 0f && lives <= 3) {
                        drawLine(
                            bodyColor,
                            start = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                                size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2
                            ),
                            end = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                                (size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH)
                            ),
                            STROKE_WIDTH,
                        )
                    }

                    drawLine(
                        bodyColor,
                        start = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD
                        ),
                        end = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - ARM_LENGTH * animatableLeftArm.value,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + ARM_ANGLE * animatableLeftArm.value
                        ),
                        STROKE_WIDTH
                    )
                    if (animatableLeftArm.value == 0f && lives <= 2) {

                        drawLine(
                            bodyColor,
                            start = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                                size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD
                            ),
                            end = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - ARM_LENGTH,
                                size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + ARM_ANGLE
                            ),
                            STROKE_WIDTH
                        )
                    }
                    drawLine(
                        bodyColor,
                        start = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD
                        ),
                        end = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH + ARM_LENGTH * animatableRightArm.value,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + ARM_ANGLE * animatableRightArm.value
                        ),
                        STROKE_WIDTH
                    )

                    if (animatableRightArm.value == 0f && lives <= 1) {
                        drawLine(
                            bodyColor,
                            start = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                                size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD
                            ),
                            end = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH + ARM_LENGTH,
                                size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + ARM_ANGLE
                            ),
                            STROKE_WIDTH
                        )
                    }

                    drawLine(
                        bodyColor,
                        start = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH
                        ),
                        end = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - HIP_WIDTH * animatableLeftLeg.value,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_ANGLE * animatableLeftLeg.value
                        ),
                        STROKE_WIDTH
                    )

                    if (animatableLeftLeg.value == 0f && lives == 0) {
                        drawLine(
                            bodyColor,
                            start = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                                size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH
                            ),
                            end = Offset(
                                size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH - HIP_WIDTH,
                                size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_ANGLE
                            ),
                            STROKE_WIDTH
                        )
                    }

                    drawLine(
                        bodyColor,
                        start = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH
                        ),
                        end = Offset(
                            size.width / HANGMAN_WIDTH_OFFSET + BEAM_LENGTH + HIP_WIDTH * animatableRightLeg.value,
                            size.height / HANGMAN_HEIGHT_OFFSET + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_ANGLE * animatableRightLeg.value
                        ),
                        STROKE_WIDTH
                    )
                }
            }
        )
    }
}
