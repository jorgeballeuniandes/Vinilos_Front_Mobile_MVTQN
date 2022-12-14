package com.grupo20.vinilos.ui.tracks

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.grupo20.vinilos.modelos.Album
import com.grupo20.vinilos.modelos.Track
import com.grupo20.vinilos.repositories.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TracksViewModel(application: Application, album: Album) :  AndroidViewModel(application) {

    private val tracksRepository = TrackRepository(application, album)

    private val _tracks = MutableLiveData<List<Track>>()

    val tracks: LiveData<List<Track>>
        get() = _tracks

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }
    fun refreshDataFromNetwork() {

        try {
            viewModelScope.launch (Dispatchers.Default) {
                withContext(Dispatchers.IO){
                    val data = tracksRepository.refreshData()
                    _tracks.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e:Exception) {
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val alb: Album) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TracksViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TracksViewModel(app, alb) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
