package com.example.vinilos_mobile.model.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos_mobile.model.models.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class VinilosApiService constructor(context: Context) {

    companion object {
        private const val BASE_URL = "https://vinilos-backend.herokuapp.com/"
        private const val COLLECTORS_PATH = "collectors"
        private const val ALBUMS_PATH = "albums"
        private const val BANDS_PATH = "bands"
        private const val MUSICIANS_PATH = "musicians"

        var instance: VinilosApiService? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: VinilosApiService(context).also {
                instance = it
            }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>> { cont ->
        requestQueue.add(
            getRequest(ALBUMS_PATH, Response.Listener<String> { response ->
                val resp = JSONArray(response)

                val albumsList = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    albumsList.add(
                        deserializeAlbum(item)
                    )
                }
                cont.resume(albumsList)

            }, Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->
        requestQueue.add(
            getRequest(COLLECTORS_PATH, Response.Listener<String> { response ->
                val resp = JSONArray(response)

                val collectorsList = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    collectorsList.add(
                        deserializeCollector(item)
                    )
                }
                cont.resume(collectorsList)

            }, Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getBands() = suspendCoroutine<List<Band>> { cont ->
        requestQueue.add(
            getRequest(BANDS_PATH, Response.Listener<String> { response ->
                val resp = JSONArray(response)

                val bandsList = mutableListOf<Band>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    bandsList.add(
                        deserializeBand(item)
                    )
                }
                cont.resume(bandsList)

            }, Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getMusicians() = suspendCoroutine<List<Musician>> { cont ->
        requestQueue.add(
            getRequest(MUSICIANS_PATH, Response.Listener<String> { response ->
                val resp = JSONArray(response)

                val musiciansList = mutableListOf<Musician>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    musiciansList.add(
                        deserializeMusician(item)
                    )
                }
                cont.resume(musiciansList)

            }, Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getAlbumDetail(albumId: Int) = suspendCoroutine<AlbumDetail> { cont ->
        requestQueue.add(
            getRequest("$ALBUMS_PATH/$albumId", Response.Listener<String> { response ->
                val resp = JSONObject(response)

                val album = deserializeAlbumDetail(resp)

                cont.resume(album)
            }, Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getCollector(collectorId: Int) = suspendCoroutine<CollectorDetail> { cont ->
        requestQueue.add(
            getRequest("$COLLECTORS_PATH/$collectorId", Response.Listener<String> { response ->
                val resp = JSONObject(response)

                val collector = deserializeCollectorDetail(resp)

                cont.resume(collector)
            }, Response.ErrorListener {
                cont.resumeWithException(it)
            })
        )
    }

    private fun getRequest(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }
}


