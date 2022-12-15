package com.anushka.tmdbclient.data.repository.artist

import android.util.Log
import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistLocalDatasource
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistRemoteDatasource
import com.anushka.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDatasource: ArtistRemoteDatasource,
    private val artistLocalDatasource: ArtistLocalDatasource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist> {
        val newListOfArtists = getArtistsFromAPI()
        artistLocalDatasource.clearAll()
        artistLocalDatasource.saveArtistsToDB(newListOfArtists)
        artistCacheDataSource.saveArtistCache(newListOfArtists)
        return getArtistsFromAPI()
    }

    suspend fun getArtistsFromAPI(): List<Artist> {
        lateinit var artistList: List<Artist>

        try {
            val response = artistRemoteDatasource.getArtists()
            val body = response.body()
            if (body != null) {
                artistList = body.artists
            }

        } catch (exception: Exception) {
            Log.i("My Tag", exception.message.toString())
        }
        return artistList
    }
    suspend fun getArtistsFromDB(): List<Artist> {
        lateinit var artistList: List<Artist>

        try {
            artistList = artistLocalDatasource.getArtistsFromDB()
        } catch (exception: Exception) {
            Log.i("My Tag", exception.message.toString())
        }

        if (artistList.size > 0) {
            return artistList
        } else {
            artistList = getArtistsFromAPI()
            artistLocalDatasource.saveArtistsToDB(artistList)
        }
        return artistList
    }



    suspend fun getArtistsFromCache(): List<Artist>? {
        lateinit var artistList: List<Artist>

        try {
            artistList = artistCacheDataSource.getArtistCache()
        } catch (exception: Exception) {
            Log.i("My Tag", exception.message.toString())
        }

        if (artistList.size > 0) {
            return artistList
        } else {
            artistList = getArtistsFromDB()
            artistCacheDataSource.saveArtistCache(artistList)
        }
        return artistList
    }


}