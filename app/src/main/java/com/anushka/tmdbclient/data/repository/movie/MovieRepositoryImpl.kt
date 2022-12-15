package com.anushka.tmdbclient.data.repository.movie

import android.util.Log
import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieLocalDatasource
import com.anushka.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource
import com.anushka.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDatasource: MovieRemoteDatasource,
    private val movieLocalDatasource: MovieLocalDatasource,
    private val movieCacheDataSource: MovieCacheDataSource,
) : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie> {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDatasource.clearAll()
        movieLocalDatasource.saveMoviesFromDB(newListOfMovies)
        movieCacheDataSource.saveMovieCache(newListOfMovies)
        return getMoviesFromAPI()
    }

    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response = movieRemoteDatasource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }

        } catch (exception: Exception) {
            Log.i("My Tag", exception.message.toString())
        }
        return movieList
    }

    suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            movieList = movieLocalDatasource.getMoviesFromDB()
        } catch (exception: Exception) {
            Log.i("My Tag", exception.message.toString())
        }

        if (movieList.size > 0) {
            return movieList
        } else {
            movieList = getMoviesFromAPI()
            movieLocalDatasource.saveMoviesFromDB(movieList)
        }
        return movieList
    }

    suspend fun getMoviesFromCache():List<Movie>{
        lateinit var movieList: List<Movie>

        try {
            movieList = movieCacheDataSource.getMovieCache()
        } catch (exception: Exception) {
            Log.i("My Tag", exception.message.toString())
        }

        if (movieList.size > 0) {
            return movieList
        } else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMovieCache(movieList)
        }
        return movieList
    }

}