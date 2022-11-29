package com.example.vinilos_mobile.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.model.repository.VinilosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository(application)

    private val _albums = MutableLiveData<Array<Album>>()

    val albums: LiveData<Array<Album>>
        get() = _albums

    init {
        viewModelScope.launch {
            refreshDataFromNetwork()
        }
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = vinilosRepository.getAlbums()
                    _albums.postValue(data)
                }
            }
        } catch (e: Exception) {
            Log.d("NETWORK_ERROR", e.toString())
        }
    }

    fun postAlbum(newAlbum: Album) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = vinilosRepository.postAlbum(newAlbum)
                }
            }
        } catch (e: Exception) {
            Log.d("NETWORK_ERROR", e.toString())
        }
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