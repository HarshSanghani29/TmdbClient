package com.anushka.tmdbclient.presentation.di.core

import com.anushka.tmdbclient.data.repository.artist.ArtistRepositoryImpl
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDatasource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDatasource
import com.anushka.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDatasource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource
import com.anushka.tmdbclient.data.repository.tvshow.TvShowRepositoryImpl
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDatasource
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDatasource
import com.anushka.tmdbclient.domain.repository.ArtistRepository
import com.anushka.tmdbclient.domain.repository.MovieRepository
import com.anushka.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDatasource,
        movieLocalDatasource: MovieLocalDatasource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {

        return MovieRepositoryImpl(
            movieRemoteDataSource,
            movieLocalDatasource,
            movieCacheDataSource
        )
    }


    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDatasource,
        tvShowLocalDatasource: TvShowLocalDatasource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository {

        return TvShowRepositoryImpl(
            tvShowRemoteDataSource,
            tvShowLocalDatasource,
            tvShowCacheDataSource
        )
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDatasource,
        artistLocalDatasource: ArtistLocalDatasource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistRepository {

        return ArtistRepositoryImpl(
            artistRemoteDataSource,
            artistLocalDatasource,
            artistCacheDataSource
        )
    }
}