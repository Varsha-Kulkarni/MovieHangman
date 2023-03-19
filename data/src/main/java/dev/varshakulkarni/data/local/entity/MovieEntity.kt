package dev.varshakulkarni.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "movie", indices = [Index(value = ["title"], unique = true)])
data class MovieEntity(
    val title: String,
    val description: String,
    val media: String?,
    val isPlayed: Boolean,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        fun populateData(): Array<MovieEntity> {
            return arrayOf(

                MovieEntity(
                    "The Clockwork Orange",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "2001: A Space Odyssey",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "The Shawshank Redemption",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "The Godfather",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "The Dark Knight",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "12 Angry Men",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "Schindler's List",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "Pulp Fiction",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "Forrest Gump",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "Fight Club",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "Inception",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "The Matrix",
                    "",
                    "",
                    false,
                ),
                MovieEntity(
                    "Goodfellas",
                    "",
                    "",
                    false,
                ),
            )
        }
    }
}
