package com.anushka.tmdbclient.presentation.di.core

import com.anushka.tmdbclient.data.api.TMDBService
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDatasource
import com.anushka.tmdbclient.data.repository.artist.datasourceImpl.ArtistRemoteDatasourceImpl
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource
import com.anushka.tmdbclient.data.repository.movie.datasourceImpl.MovieRemoteDatasourceImpl
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDatasource
import com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl.TvShowRemoteDatasourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDatasource{
        return MovieRemoteDatasourceImpl(
            tmdbService, apiKey
        )
    }

    @Singleton
    @Provides
    fun provideTvRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDatasource {
        return TvShowRemoteDatasourceImpl(
            tmdbService, apiKey
        )
    }

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDatasource {
        return ArtistRemoteDatasourceImpl(
            tmdbService, apiKey
        )
    }
}