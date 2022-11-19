package com.grupo20.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.grupo20.vinilos.modelos.Album
import com.grupo20.vinilos.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    suspend fun refreshData():List<Album> {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
      return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
}