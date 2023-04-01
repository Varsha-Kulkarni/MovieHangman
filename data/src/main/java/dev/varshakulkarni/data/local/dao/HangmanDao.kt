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
