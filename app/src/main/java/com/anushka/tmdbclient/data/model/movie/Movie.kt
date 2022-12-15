package com.anushka.tmdbclient.data.model.movie


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

@Entity(tableName = "popular_movies")
data class Movie(

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("overview")
    @Expose
    val overview: String?,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String?,

    @SerializedName("title")
    @Expose
    val title: String?
)