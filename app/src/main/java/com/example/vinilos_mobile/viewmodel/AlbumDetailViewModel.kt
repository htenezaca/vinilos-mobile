package com.example.vinilos_mobile.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.AlbumDetail
import com.example.vinilos_mobile.model.repository.VinilosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AlbumDetailViewModel(application: Application, albumId: Int) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository(application)

    private val _album = MutableLiveData<AlbumDetail>()

    val album: LiveData<AlbumDetail>
        get() = _album

    init {
        viewModelScope.launch {
            refreshDataFromNetwork(albumId)
        }
    }

    private fun refreshDataFromNetwork(albumId: Int) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = vinilosRepository.getAlbum(albumId)
                    _album.postValue(data)
                }
            }
        } catch (e: Exception) {
            Log.d("NETWORK_ERROR", e.toString())
        }
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
