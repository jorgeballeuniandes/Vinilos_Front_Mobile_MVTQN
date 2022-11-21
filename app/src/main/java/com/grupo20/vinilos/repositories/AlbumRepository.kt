package com.grupo20.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.grupo20.vinilos.modelos.Album

import com.grupo20.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumRepository (val application: Application){
    suspend fun refreshData():List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
      return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
    suspend fun createAlbum( requesData:Map<Any?, Any?>, onComplete:(resp:JSONObject)->Unit, onError: (error:VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        val requestObject : JSONObject = JSONObject(requesData)
        return NetworkServiceAdapter.getInstance(application).postAlbum(requestObject, onComplete, onError)
    }
}