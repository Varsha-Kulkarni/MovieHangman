package dev.varshakulkarni.core.repository

import dev.varshakulkarni.core.model.Movie
import kotlinx.coroutines.flow.Flow

interface HangmanDataSource {
    fun getMovie(): Flow<Movie>
    suspend fun updateMovie(movie: Movie)
}