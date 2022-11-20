package com.grupo20.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.grupo20.vinilos.modelos.Collector
import com.grupo20.vinilos.network.NetworkServiceAdapter

class CollectorsRepository (val application: Application){
    suspend fun refreshData():List<Collector>{
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }
}