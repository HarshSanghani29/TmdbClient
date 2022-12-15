package com.anushka.tmdbclient.data.repository.tvshow.datasource

import com.anushka.tmdbclient.data.model.tvshow.TvShow

interface TvShowLocalDatasource {
    suspend fun getTvShowsFromDB():List<TvShow>
    suspend fun saveTvShowsFromDB(tvShows:List<TvShow>)
    suspend fun clearAll()
}
