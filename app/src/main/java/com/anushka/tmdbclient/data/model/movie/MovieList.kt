package com.anushka.tmdbclient.data.model.movie


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    @Expose
    val movies: List<Movie>
)