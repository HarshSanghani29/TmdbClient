package com.anushka.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anushka.tmdbclient.domain.usecase.GetArtistsUseCase
import com.anushka.tmdbclient.domain.usecase.GetMoviesUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.anushka.tmdbclient.domain.usecase.UpdateMoviesUseCase

class ArtistViewModel(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
): ViewModel() {

    fun getArtists() = liveData {
        val artistList = getArtistsUseCase.execute()
        emit(artistList)
    }

    fun updateArtists() = liveData {
        val artistList = updateArtistsUseCase.execute()
        emit(artistList)
    }
}