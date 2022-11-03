package com.Equipo20.vinilos_front_mobile_mvtqn.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.Equipo20.vinilos_front_mobile_mvtqn.models.Album
import com.Equipo20.vinilos_front_mobile_mvtqn.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}