package com.example.vinilos_mobile.model.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.model.models.deserializeAlbum
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

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: VinilosApiService(context).also {
                    instance = it
                }
            }

        fun getAlbumDetail(
            albumId: Int,
            responseListener: Response.Listener<JSONObject>,
            errorListener: Response.ErrorListener
        ): JsonObjectRequest {
            return JsonObjectRequest("$BASE_URL$ALBUMS_PATH/$albumId", responseListener, errorListener)
        }

        fun getBands(
            responseListener: Response.Listener<JSONArray>,
            errorListener: Response.ErrorListener
        ): JsonArrayRequest {
            return JsonArrayRequest(BASE_URL + BANDS_PATH, responseListener, errorListener)
        }

        fun getMusicians(
            responseListener: Response.Listener<JSONArray>,
            errorListener: Response.ErrorListener
        ): JsonArrayRequest {
            return JsonArrayRequest(BASE_URL + MUSICIANS_PATH, responseListener, errorListener)
        }

        fun getCollectors(
            responseListener: Response.Listener<JSONArray>,
            errorListener: Response.ErrorListener
        ): JsonArrayRequest {
            return JsonArrayRequest(BASE_URL + COLLECTORS_PATH, responseListener, errorListener)
        }

        fun getCollector(
            collectorId: Int,
            responseListener: Response.Listener<JSONObject>,
            errorListener: Response.ErrorListener
        ): JsonObjectRequest {
            return JsonObjectRequest(
                "$BASE_URL$COLLECTORS_PATH/$collectorId",
                responseListener,
                errorListener
            )
        }
    }

    //ToDo privatize
    public val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>> { cont ->
        requestQueue.add(getRequest(ALBUMS_PATH,
        Response.Listener<String> { response ->
            val resp = JSONArray(response)

            val albumsList = mutableListOf<Album>()
            for (i in 0 until resp.length()) {
                val item = resp.getJSONObject(i)
                albumsList.add(
                    deserializeAlbum(item)
                )
            }
            cont.resume(albumsList)

        },
        Response.ErrorListener {
            cont.resumeWithException(it)
        }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
}


