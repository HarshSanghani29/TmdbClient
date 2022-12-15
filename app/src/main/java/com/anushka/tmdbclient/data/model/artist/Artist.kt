package com.anushka.tmdbclient.data.model.artist


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popular_artists")
data class Artist(

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,

//    @ColumnInfo("artist_name")
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("profile_path")
    @Expose
    val profilePath: String
)