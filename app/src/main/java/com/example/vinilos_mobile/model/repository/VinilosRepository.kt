package com.example.vinilos_mobile.model.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.vinilos_mobile.model.api.VinilosApiService
import com.example.vinilos_mobile.model.models.*
import org.json.JSONObject

class VinilosRepository (val applicationContext: Application) {

    fun getCollectors(
        applicationContext: Context,
        onComplete: (resp: List<Collector>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {

        var vinilosApiService = VinilosApiService.getInstance(applicationContext)
        vinilosApiService.requestQueue.add(VinilosApiService.getCollectors(
            { response ->
                Log.d("GET COLLECTORS", "response: $response")
                val collectorsList = mutableListOf<Collector>()

                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    collectorsList.add(
                        deserializeCollector(item)
                    )
                }

                onComplete(collectorsList)
            },
            {
                Log.d("GET COLLECTORS", "error: $it")
                onError(it)
            }
        ))
    }

    fun getCollector(
        collectorId: Int,
        applicationContext: Context,
        onComplete: (resp: CollectorDetail) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        var vinilosApiService = VinilosApiService.getInstance(applicationContext)
        vinilosApiService.requestQueue.add(VinilosApiService.getCollector(
            collectorId,
            { response ->
                Log.d("GET COLLECTOR", "response: $response")
                val collector = deserializeCollectorDetail(response)
                onComplete(collector)
            },
            {
                Log.d("GET COLLECTOR", "error: $it")
                onError(it)
            }
        ))
    }

    suspend fun getAlbums(): List<Album> {
        return VinilosApiService.getInstance(applicationContext).getAlbums()
    }

    fun getAlbum(
        albumId: Int,
        applicationContext: Context,
        onComplete: (resp: AlbumDetail) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        var vinilosApiService = VinilosApiService.getInstance(applicationContext)
        vinilosApiService.requestQueue.add(VinilosApiService.getAlbumDetail(
            albumId,
            { response ->
                Log.d("GET ALBUM DETAIL", "response: $response")
                if (response != null) {
                    onComplete(
                        deserializeAlbumDetail(response)
                    )
                } else {
                    onError(VolleyError("No se encontró el álbum"))
                }
            },
            {
                Log.d("GET ALBUM", "error: $it")
                onError(it)
            }
        ))

    }

    fun getPerformers(
        applicationContext: Context,
        onComplete: (resp: List<Performer>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        var vinilosApiService = VinilosApiService.getInstance(applicationContext)
        val performersList = mutableListOf<Performer>()

        vinilosApiService.requestQueue.add(VinilosApiService.getMusicians(
            { response ->
                Log.d("GET MUSICIANS", "response: $response")
                performersList.addAll(
                    deserializePerformers(response)
                )

            },
            {
                Log.d("GET MUSICIANS", "error: $it")
                onError(it)
            }
        ))
        vinilosApiService.requestQueue.add(VinilosApiService.getBands(
            { response ->
                Log.d("GET BANDS", "response: $response")
                performersList.addAll(
                    deserializePerformers(response)
                )
                onComplete(performersList)
            },
            {
                Log.d("GET BANDS", "error: $it")
                onError(it)
            }
        ))
    }
}
