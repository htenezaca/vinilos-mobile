package com.example.vinilos_mobile.model.repository

import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_mobile.model.api.VinilosApiService
import com.example.vinilos_mobile.model.models.*
import org.json.JSONObject

class VinilosRepository {

    fun getCollectors(
        applicationContext: Context,
        onComplete: (resp: List<Collector>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {

        var vinilosApiService = VinilosApiService(applicationContext)

        vinilosApiService.instance.add(VinilosApiService.getCollectors(
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
        var vinilosApiService = VinilosApiService(applicationContext)
        vinilosApiService.instance.add(VinilosApiService.getCollector(
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

    fun getAlbums(
        applicationContext: Context,
        onComplete: (resp: List<Album>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {

        var vinilosApiService = VinilosApiService(applicationContext)

        vinilosApiService.instance.add(VinilosApiService.getAlbums(
            { response ->
                Log.d("GET ALBUMS", "response: $response")
                val albumsList = mutableListOf<Album>()

                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    albumsList.add(
                        deserializeAlbum(item)
                    )
                }

                onComplete(albumsList)
            },
            {
                Log.d("GET ALBUMS", "error: $it")
                onError(it)
            }
        ))
    }

    fun getAlbum(
        albumId: Int,
        applicationContext: Context,
        onComplete: (resp: AlbumDetail) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        var vinilosApiService = VinilosApiService(applicationContext)
        vinilosApiService.instance.add(VinilosApiService.getAlbumDetail(
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
        var vinilosApiService = VinilosApiService(applicationContext)
        val performersList = mutableListOf<Performer>()

        vinilosApiService.instance.add(VinilosApiService.getMusicians(
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
        vinilosApiService.instance.add(VinilosApiService.getBands(
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

    fun getMusician(
        musicianId: Int,
        applicationContext: Context,
        onComplete: (resp: MusicianDetail) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        var vinilosApiService = VinilosApiService(applicationContext)
        vinilosApiService.instance.add(VinilosApiService.getMusicianDetail(
            musicianId,
            { response ->
                Log.d("GET MUSICIAN DETAIL", "response: $response")
                if (response != null) {
                    onComplete(
                        deserializeMusicianDetail(response)
                    )
                } else {
                    onError(VolleyError("No se encontró el músico"))
                }
            },
            {
                Log.d("GET MUSICIAN", "error: $it")
                onError(it)
            }
        ))

    }
}
