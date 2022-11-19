package com.grupo20.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.grupo20.vinilos.modelos.Album
import com.grupo20.vinilos.modelos.Artist
import com.grupo20.vinilos.modelos.Collector
import com.grupo20.vinilos.network.NetworkServiceAdapter

class ArtistsRepository (val application: Application){

     suspend fun refreshData():List<Artist> {
        return NetworkServiceAdapter.getInstance(application).getArtists()
    }

}