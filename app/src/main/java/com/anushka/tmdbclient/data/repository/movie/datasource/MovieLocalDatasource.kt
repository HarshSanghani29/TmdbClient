package com.anushka.tmdbclient.data.repository.movie.datasource

import com.anushka.tmdbclient.data.model.movie.Movie

interface MovieLocalDatasource {
    suspend fun getMoviesFromDB():List<Movie>
    suspend fun saveMoviesFromDB(movies:List<Movie>)
    suspend fun clearAll()
}