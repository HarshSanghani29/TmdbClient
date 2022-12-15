package com.anushka.tmdbclient.data.repository.tvshow.datasourceImpl

import com.anushka.tmdbclient.data.db.TvShowDao
import com.anushka.tmdbclient.data.model.tvshow.TvShow
import com.anushka.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDatasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDatasourceImpl(private val tvShowDao: TvShowDao): TvShowLocalDatasource {

    override suspend fun getTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getTvShows()
    }

    override suspend fun saveTvShowsFromDB(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvShows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTvShows()
        }
    }

}