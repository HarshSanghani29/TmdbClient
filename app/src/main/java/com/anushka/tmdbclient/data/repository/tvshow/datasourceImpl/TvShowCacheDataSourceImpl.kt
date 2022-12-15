package com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl

import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource

class TvShowCacheDataSourceImpl: TvShowCacheDataSource {

    private var tvShowList = ArrayList<TvShow>()

    override suspend fun getTvShowCache(): List<TvShow> {
        return tvShowList
    }

    override suspend fun saveTvShowCache(tvShow: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShow)

    }
}