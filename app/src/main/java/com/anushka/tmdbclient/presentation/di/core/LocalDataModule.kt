package com.anushka.tmdbclient.presentation.di.core

import com.anushka.tmdbclient.data.db.ArtistDao
import com.anushka.tmdbclient.data.db.MovieDao
import com.anushka.tmdbclient.data.db.TvShowDao
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDatasource
import com.anushka.tmdbclient.data.repository.artist.datasourceImpl.ArtistLocalDatasourceImpl
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDatasource
import com.anushka.tmdbclient.data.repository.movie.datasourceImpl.MovieLocalDatasourceImpl
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDatasource
import com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowLocalDatasourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao):MovieLocalDatasource{
        return MovieLocalDatasourceImpl(movieDao)
    }

    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao):TvShowLocalDatasource{
        return TvShowLocalDatasourceImpl(tvShowDao)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDatasource {
        return ArtistLocalDatasourceImpl(artistDao)
    }
}