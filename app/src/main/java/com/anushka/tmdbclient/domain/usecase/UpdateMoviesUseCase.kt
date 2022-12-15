package com.anushka.tmdbclient.domain.usecase

import com.anushka.tmdbclient.data.model.movie.Movie
import com.anushka.tmdbclient.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val moviesRepository: MovieRepository) {

    suspend fun execute():List<Movie>? = moviesRepository.updateMovies()

}