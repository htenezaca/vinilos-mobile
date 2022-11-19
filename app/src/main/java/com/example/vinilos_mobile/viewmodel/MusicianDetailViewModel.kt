package com.example.vinilos_mobile.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.MusicianDetail
import com.example.vinilos_mobile.model.repository.VinilosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MusicianDetailViewModel(application: Application, musicianId: Int) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository(application)

    private val _musician = MutableLiveData<MusicianDetail>()

    val musician: LiveData<MusicianDetail>
        get() = _musician

    init {
        viewModelScope.launch {
            refreshDataFromNetwork(musicianId)
        }
    }

    private fun refreshDataFromNetwork(musicianId: Int) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = vinilosRepository.getMusician(musicianId)
                    _musician.postValue(data)
                }
            }
        } catch (e: Exception) {
            Log.d("NETWORK_ERROR", e.toString())
        }
    }

    class Factory(val app: Application, private val musicianId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MusicianDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MusicianDetailViewModel(app, musicianId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
