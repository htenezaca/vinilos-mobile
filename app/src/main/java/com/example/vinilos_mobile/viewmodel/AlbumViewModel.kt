package com.example.vinilos_mobile.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.model.repository.VinilosRepository
import kotlinx.coroutines.launch


class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository()

    private val _albums = MutableLiveData<List<Album>>()

    val albums: LiveData<List<Album>>
        get() = _albums

    init {
        viewModelScope.launch {
            refreshDataFromNetwork()
        }
    }

    private fun refreshDataFromNetwork() {
        vinilosRepository.getAlbums(getApplication(), { albumsResponse ->
            _albums.postValue(albumsResponse)
        },
            {
                Log.d("NETWORK_ERROR", it.toString())
            })
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}