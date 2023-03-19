package dev.varshakulkarni.moviehangman.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.varshakulkarni.core.repository.HangmanDataSource
import dev.varshakulkarni.repository.HangmanRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHangmanRepository(hangmanRepository: HangmanRepository): HangmanDataSource
}