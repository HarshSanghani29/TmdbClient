package com.anushka.tmdbclient.data.model.tvshow


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

@Entity(tableName = "popular_tvShows")
data class TvShow(

    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String,

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("overview")
    @Expose
    val overview: String?,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?
)