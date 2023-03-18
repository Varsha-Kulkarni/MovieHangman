package dev.varshakulkarni.core.repository

import dev.varshakulkarni.core.model.Movie
import kotlinx.coroutines.flow.Flow

interface HangmanDataSource {
    fun getMovie(): Flow<Movie>
    fun updateMovie(movie: Movie): Result<Unit>
}