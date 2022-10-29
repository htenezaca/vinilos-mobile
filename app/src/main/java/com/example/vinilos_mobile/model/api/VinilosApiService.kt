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

        fun getCollectors(
            responseListener: Response.Listener<JSONArray>,
            errorListener: Response.ErrorListener
        ): JsonArrayRequest {
            return JsonArrayRequest(BASE_URL + COLLECTORS_PATH, responseListener, errorListener)
        }

    }

}