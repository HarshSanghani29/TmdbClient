package com.anushka.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.anushka.tmdbclient.data.db.ArtistDao
import com.anushka.tmdbclient.data.db.MovieDao
import com.anushka.tmdbclient.data.db.TMDBDatabase
import com.anushka.tmdbclient.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideMovieDataBase(context: Context): TMDBDatabase{
        return Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclient")
        .build()
    }

    @Singleton
    @Provides
    fun ProvideMovieDao(tmdbDatabase: TMDBDatabase):MovieDao{
        return tmdbDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun ProvideTvDao(tmdbDatabase: TMDBDatabase): TvShowDao {
        return tmdbDatabase.tvDao()
    }

    @Singleton
    @Provides
    fun ProvideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDao {
        return tmdbDatabase.artistDao()
    }
}