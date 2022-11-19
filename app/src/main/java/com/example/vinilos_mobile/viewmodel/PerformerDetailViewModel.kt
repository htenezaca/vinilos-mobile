package com.example.vinilos_mobile.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos_mobile.model.models.PerformerDetail
import com.example.vinilos_mobile.model.repository.VinilosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PerformerDetailViewModel(application: Application, performerId: Int, isBand: Boolean) : AndroidViewModel(application) {

    private val vinilosRepository: VinilosRepository = VinilosRepository(application)

    private val _performer = MutableLiveData<PerformerDetail>()

    val performer: LiveData<PerformerDetail>
        get() = _performer

    init {
        viewModelScope.launch {
            refreshDataFromNetwork(performerId, isBand)
        }
    }

    private fun refreshDataFromNetwork(performerId: Int, isBand: Boolean) {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = vinilosRepository.getPerformer(performerId, isBand)
                    _performer.postValue(data)
                }
            }
        } catch (e: Exception) {
            Log.d("NETWORK_ERROR", e.toString())
        }
    }

    class Factory(val app: Application, private val performerId: Int, private val isBand: Boolean) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PerformerDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PerformerDetailViewModel(app, performerId,isBand) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
