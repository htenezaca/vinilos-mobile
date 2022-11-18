package com.example.vinilos_mobile.model.api

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class VinilosApiService constructor(context: Context) {
    val instance: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    companion object {
        private const val BASE_URL = "https://vinilos-backend.herokuapp.com/"
        private const val COLLECTORS_PATH = "collectors"
        private const val ALBUMS_PATH = "albums"
        private const val BANDS_PATH = "bands"
        private const val MUSICIANS_PATH = "musicians"

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
            return JsonObjectRequest("$BASE_URL$COLLECTORS_PATH/$collectorId", responseListener, errorListener)
        }

        fun getAlbums(
            responseListener: Response.Listener<JSONArray>,
            errorListener: Response.ErrorListener
        ): JsonArrayRequest {
            return JsonArrayRequest(BASE_URL + ALBUMS_PATH, responseListener, errorListener)
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

        fun getMusicianDetail(
            musicianId: Int,
            responseListener: Response.Listener<JSONObject>,
            errorListener: Response.ErrorListener
        ): JsonObjectRequest {
            return JsonObjectRequest("$BASE_URL$MUSICIANS_PATH/$musicianId", responseListener, errorListener)
        }
    }

}
