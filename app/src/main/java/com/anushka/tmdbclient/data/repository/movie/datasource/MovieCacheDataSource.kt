package com.anushka.tmdbclient.data.repository.movie.datasource

import com.anushka.tmdbclient.data.model.movie.Movie

interface MovieCacheDataSource {

    suspend fun getMovieCache():List<Movie>
    suspend fun saveMovieCache(movie:List<Movie>)
}