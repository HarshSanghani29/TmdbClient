package com.anushka.tmdbclient.data.repository.artist.datasource

import com.anushka.tmdbclient.data.model.artist.Artist

interface ArtistCacheDataSource {

    suspend fun getArtistCache():List<Artist>
    suspend fun saveArtistCache(artists:List<Artist>)
}