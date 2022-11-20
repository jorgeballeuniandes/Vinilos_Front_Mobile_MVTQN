package com.grupo20.vinilos.ui.albums.create_album

import android.app.Application
import androidx.lifecycle.*
import com.android.volley.VolleyError
import com.grupo20.vinilos.modelos.Artist
import com.grupo20.vinilos.repositories.AlbumRepository
import com.grupo20.vinilos.repositories.ArtistsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class CreateAlbumViewModel (application: Application) :  AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application)

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown
    fun enviarFormulario(dataMap:Map<Any?, Any?>, onComplete:(resp:JSONObject)->Unit, onError: (error:VolleyError)->Unit){
        try {
            viewModelScope.launch (Dispatchers.Default) {
                withContext(Dispatchers.IO){
                    var data = albumsRepository.createAlbum(dataMap, onComplete, onError)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        } catch (e:Exception){
            _eventNetworkError.value=true
        }
    }
}