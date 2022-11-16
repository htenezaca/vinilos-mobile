package com.example.vinilos_mobile.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.AlbumDetail
import com.example.vinilos_mobile.model.repository.VinilosRepository
import kotlinx.coroutines.launch


class AlbumDetailViewModel(application: Application, albumId: Int) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository()

    private val _album = MutableLiveData<AlbumDetail>()

    val album: LiveData<AlbumDetail>
        get() = _album

    init {
        viewModelScope.launch {
            refreshDataFromNetwork(albumId)
        }
    }

    private fun refreshDataFromNetwork(albumId: Int) {
        vinilosRepository.getAlbum(albumId, getApplication(), { albumsResponse ->
            // TODO: Actually use the API method
            _album.postValue(albumsResponse)
        },
            {
                Log.d("NETWORK_ERROR", it.toString())
            })
    }

    class Factory(val app: Application, private val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
