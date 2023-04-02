package dev.varshakulkarni.repository

import dev.varshakulkarni.core.model.Movie
import dev.varshakulkarni.core.repository.HangmanDataSource
import dev.varshakulkarni.data.local.dao.HangmanDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HangmanRepository @Inject constructor(private val hangmanDao: HangmanDao): HangmanDataSource {
    override suspend fun getMovie(): Flow<Movie?> = hangmanDao.getMovie()
        .map { it?.let { Movie(it.title, it.description, it.media, it.isPlayed) } }


    override suspend fun updateMovie(movie: Movie) {
        hangmanDao.updateMovie(movie.title)
    }
}