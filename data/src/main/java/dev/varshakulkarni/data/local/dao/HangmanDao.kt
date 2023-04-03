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
package dev.varshakulkarni.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.varshakulkarni.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HangmanDao {

    /**
     * The return type of this method is nullable because internally it throws an error if
     * entity doesn't exist.
     *
     * Official docs says
     *
     * * When the return type is Flow<T>, querying an empty table throws a null pointer exception.
     * * When the return type is Flow<T?>, querying an empty table emits a null value.
     * * When the return type is Flow<List<T>>, querying an empty table emits an empty list.
     *
     * Refer: https://developer.android.com/reference/androidx/room/Query
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(varargs: Array<MovieEntity>?)

    @Query("SELECT * FROM movie WHERE isPlayed = 0 ORDER BY Random() Limit 1")
    fun getMovie(): Flow<MovieEntity?>

    @Query("UPDATE movie SET isPlayed = 1 WHERE title = :title")
    suspend fun updateMovie(title: String)

    @Query("SELECT * FROM movie WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<MovieEntity>>

    @Delete
    fun deleteMovie(entry: MovieEntity)
}
