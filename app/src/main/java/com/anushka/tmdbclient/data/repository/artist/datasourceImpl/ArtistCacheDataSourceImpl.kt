package com.anushka.tmdbclient.data.repository.artist.datasourceImpl

import com.anushka.tmdbclient.data.model.artist.Artist
import com.anushka.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource

class ArtistCacheDataSourceImpl : ArtistCacheDataSource {

    private var artistList = ArrayList<Artist>()

    override suspend fun getArtistCache(): List<Artist> {

        return artistList

    }

    override suspend fun saveArtistCache(artist: List<Artist>) {

        artistList.clear()
        artistList = ArrayList(artist)
    }
}