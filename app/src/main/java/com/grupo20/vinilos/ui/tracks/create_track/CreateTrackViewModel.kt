package com.grupo20.vinilos.ui.tracks.create_track

import android.app.Application
import androidx.lifecycle.*
import com.android.volley.VolleyError
import com.grupo20.vinilos.modelos.Album
import com.grupo20.vinilos.repositories.TrackRepository
import com.grupo20.vinilos.ui.albums.AlbumsAdapter
import com.grupo20.vinilos.ui.tracks.TrackAdapter
import com.grupo20.vinilos.ui.tracks.TracksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class CreateTrackViewModel (application: Application, album: Album) :  AndroidViewModel(application) {
    private val trackRepository = TrackRepository(application, album)
    private var tracksViewModelAdapter: TrackAdapter? = null

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    fun enviarFormulario(dataMap:Map<Any?, Any?>, onComplete:(resp: JSONObject)->Unit, onError: (error: VolleyError)->Unit){
        try {
            viewModelScope.launch (Dispatchers.Default) {
                withContext(Dispatchers.IO){
                    var data = trackRepository.createTrack(dataMap, onComplete, onError)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e:Exception){
            _eventNetworkError.value=true
        }
    }

    class Factory(val app: Application, val alb: Album) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CreateTrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CreateTrackViewModel(app, alb) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}