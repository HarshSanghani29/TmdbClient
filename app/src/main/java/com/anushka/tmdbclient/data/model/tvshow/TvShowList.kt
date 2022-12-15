package com.anushka.tmdbclient.data.model.tvshow


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class TvShowList(

    @SerializedName("results")
    @Expose
    val tvShows: List<TvShow>

)