package com.example.vinilos_mobile.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos_mobile.model.models.Track
import com.example.vinilos_mobile.model.repository.VinilosRepository

class AddTrackAlbumViewModel(application: Application, private val albumId: Int) : ViewModel() {
    private val vinilosRepository: VinilosRepository = VinilosRepository(application)

    suspend fun addTrack(name: String, minutes: String, seconds: String): Track {
        val duration = "$minutes:$seconds"
        val track = Track(0, name, duration)
        return vinilosRepository.addTrackToAlbum(albumId, track)
    }

    class Factory(val app: Application, private val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddTrackAlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddTrackAlbumViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}