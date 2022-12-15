package com.anushka.tmdbclient.data.repository.tvshow.datasource

import com.anushka.tmdbclient.data.model.tvshow.TvShow

interface TvShowCacheDataSource {
     suspend fun getTvShowCache():List<TvShow>
     suspend fun saveTvShowCache(tvShow:List<TvShow>)
}
