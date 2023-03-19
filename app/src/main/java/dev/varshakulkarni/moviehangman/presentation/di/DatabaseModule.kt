package dev.varshakulkarni.moviehangman.presentation.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.varshakulkarni.data.local.HangmanDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = HangmanDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideHangmanDao(database: HangmanDatabase) = database.hangmanDao()
}