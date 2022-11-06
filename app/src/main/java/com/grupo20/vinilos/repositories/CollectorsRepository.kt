package com.grupo20.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.grupo20.vinilos.modelos.Collector
import com.grupo20.vinilos.network.NetworkServiceAdapter

class CollectorsRepository (val application: Application){
    fun refreshData(callback: (List<Collector>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getCollectors({
            callback(it)
        },
            onError
        )
    }
}