package com.anushka.tmdbclient.data.repository.movie.datasourceImpl

import com.anushka.tmdbclient.data.db.MovieDao
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDatasourceImpl(private val movieDao: MovieDao): MovieLocalDatasource {
    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getMovies()
    }

    override suspend fun saveMoviesFromDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }
}