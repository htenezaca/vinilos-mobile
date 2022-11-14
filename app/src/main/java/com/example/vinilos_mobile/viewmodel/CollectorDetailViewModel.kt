package com.example.vinilos_mobile.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.AlbumDetail
import com.example.vinilos_mobile.model.models.CollectorDetail
import com.example.vinilos_mobile.model.repository.VinilosRepository
import kotlinx.coroutines.launch

class CollectorDetailViewModel(application: Application, collectorId: Int) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository()

    private val _collector = MutableLiveData<CollectorDetail>()

    val collector: LiveData<CollectorDetail>
        get() = _collector

    init {
        refreshDataFromNetwork(collectorId)
    }

    private fun refreshDataFromNetwork(collectorId: Int) {
        vinilosRepository.getCollector(collectorId, getApplication(), { collectorResponse ->
            _collector.postValue(collectorResponse)
        },
            {
                Log.d("NETWORK_ERROR", it.toString())
            })
    }

    class Factory(val app: Application, private val collectorId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorDetailViewModel(app, collectorId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}


