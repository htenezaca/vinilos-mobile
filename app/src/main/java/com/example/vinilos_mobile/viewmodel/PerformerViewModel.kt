package com.example.vinilos_mobile.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.Performer
import com.example.vinilos_mobile.model.repository.VinilosRepository

class PerformerViewModel(application: Application) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository()

    private val _performers = MutableLiveData<List<Performer>>()

    val performers: LiveData<List<Performer>>
        get() = _performers

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        vinilosRepository.getPerformers(getApplication(), { performersResponse ->

            Log.d("Init","init hola" + performersResponse.toString())
            _performers.postValue(performersResponse)
        },
            {
                Log.d("NETWORK_ERROR", it.toString())
            })
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PerformerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PerformerViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
