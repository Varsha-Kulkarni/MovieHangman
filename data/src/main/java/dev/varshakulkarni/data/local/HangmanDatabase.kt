package dev.varshakulkarni.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.varshakulkarni.data.local.dao.HangmanDao
import dev.varshakulkarni.data.local.entity.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(
    entities = [MovieEntity::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class HangmanDatabase : RoomDatabase() {

    abstract fun hangmanDao(): HangmanDao

    companion object {
        private const val DB_NAME = "Hangman.db"

        @Volatile
        private var INSTANCE: HangmanDatabase? = null

        fun getInstance(context: Context): HangmanDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HangmanDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS)
                    .addCallback(
                    HangmanDatabaseCallback(CoroutineScope(Dispatchers.IO))).build()

                INSTANCE = instance
                return instance
            }
        }

        private class HangmanDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.hangmanDao())
                    }
                }
            }
        }

        fun populateDatabase(hangmanDao: HangmanDao) {
            hangmanDao.insertMovies(MovieEntity.populateData())
        }

    }
}

