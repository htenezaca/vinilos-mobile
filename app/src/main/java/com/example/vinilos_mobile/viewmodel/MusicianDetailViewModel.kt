package com.example.vinilos_mobile.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.MusicianDetail
import com.example.vinilos_mobile.model.repository.VinilosRepository
import kotlinx.coroutines.launch


class MusicianDetailViewModel(application: Application, musicianId: Int = 100) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository()

    private val _musician = MutableLiveData<MusicianDetail>()

    val musician: LiveData<MusicianDetail>
        get() = _musician

    init {
        viewModelScope.launch {
            refreshDataFromNetwork(musicianId)
        }
    }

    private fun refreshDataFromNetwork(musicianId: Int) {
        vinilosRepository.getMusician(musicianId, getApplication(), { musiciansResponse ->
            // TODO: Actually use the API method
            _musician.postValue(musiciansResponse)
        },
            {
                Log.d("NETWORK_ERROR", it.toString())
            })
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
