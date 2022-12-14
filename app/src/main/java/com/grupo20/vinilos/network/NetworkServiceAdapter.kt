package com.grupo20.vinilos.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.grupo20.vinilos.modelos.*

import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://vinilos-back-end.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    suspend fun getAlbums():List<Album> = suspendCoroutine {  cont->
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", java.util.Locale.getDefault())
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = format.parse(item.getString("releaseDate")) as Date, genre = item.getString("genre"), description = item.getString("description")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getTracks(album: Int):List<Track> = suspendCoroutine { cont->
        requestQueue.add(getRequest(
            "albums/$album/tracks",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Track>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Track(trackId = item.getInt("id"),name = item.getString("name"), duration = item.getString("duration")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getCollectors():List<Collector> = suspendCoroutine <List<Collector>>{ cont->

        requestQueue.add(getRequest("collectors",
            { response ->
                Log.d("tagb", response)
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Collector(collectorId = item.getInt("id"),name = item.getString("name"), telephone = item.getString("telephone"), email = item.getString("email")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
                Log.d("", it.message.toString())
            }))

    }
    fun getComments(albumId:Int, onComplete:(resp:List<Comment>)->Unit, onError: (error:VolleyError)->Unit) {
        requestQueue.add(getRequest("albums/$albumId/comments",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Comment>()
                var item:JSONObject?
                for (i in 0 until resp.length()) {
                    item = resp.getJSONObject(i)
                    Log.d("Response", item.toString())
                    list.add(i, Comment(albumId = albumId, rating = item.getInt("rating").toString(), description = item.getString("description")))
                }
                onComplete(list)
            },
            {
                onError(it)
            }))
    }

    suspend fun getArtists():List<Artist> = suspendCoroutine{ cont->
        requestQueue.add(getRequest("musicians",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Artist>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", java.util.Locale.getDefault())
                    list.add(i, Artist(artistId = item.getInt("id"),name = item.getString("name"), description = item.getString("description"), image = item.getString("image"), birthDate = format.parse(item.getString("birthDate")) as Date))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))

    }
    fun postComment(body: JSONObject, albumId: Int,  onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums/$albumId/comments",
            body,
            { response ->
                onComplete(response)
            },
            {
                onError(it)
            }))
    }

    fun postAlbum(body: JSONObject,  onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        Log.d("QUESO", "postAlbum: " + body.toString())
        requestQueue.add(postRequest("albums",
            body,
            { response ->
                onComplete(response)
            },
            {
                onError(it)
            }))
    }

    fun postTrack(album: Int, body: JSONObject,  onComplete:(resp:JSONObject)->Unit , onError: (error:VolleyError)->Unit){
        Log.d("QUESO", "postAlbum: " + body.toString())
        requestQueue.add(postRequest("albums/$album/tracks",
            body,
            { response ->
                onComplete(response)
            },
            {
                onError(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }
}