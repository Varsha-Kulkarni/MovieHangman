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
package dev.varshakulkarni.repository

import dev.varshakulkarni.core.model.Movie
import dev.varshakulkarni.core.repository.HangmanDataSource
import dev.varshakulkarni.data.local.dao.HangmanDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HangmanRepository @Inject constructor(private val hangmanDao: HangmanDao) :
    HangmanDataSource {
    override suspend fun getMovie(): Flow<Movie?> = hangmanDao.getMovie()
        .map { it?.let { Movie(it.title, it.description, it.media, it.isPlayed) } }

    override suspend fun updateMovie(movie: Movie) {
        hangmanDao.updateMovie(movie.title)
    }
}
