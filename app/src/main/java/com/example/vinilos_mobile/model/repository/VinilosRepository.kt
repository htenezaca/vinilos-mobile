package com.example.vinilos_mobile.model.repository

import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_mobile.model.api.VinilosApiService
import com.example.vinilos_mobile.model.models.*

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
                        Collector(
                            collectorId = item.getInt("id"),
                            name = item.getString("name"),
                            telephone = item.getString("telephone"),
                            email = item.getString("email")
                        )
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
                        Album(
                            albumId = item.getInt("id"),
                            name = item.getString("name"),
                            cover = item.getString("cover"),
                            releaseDate = item.getString("releaseDate"),
                            description = item.getString("description"),
                            genre = item.getString("genre"),
                            recordLabel = item.getString("recordLabel")
                        )
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

    fun getPerformers(
        applicationContext: Context,
        onComplete: (resp: List<Performer>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        var vinilosApiService = VinilosApiService(applicationContext)
        val performersList = mutableListOf<Performer>()

        vinilosApiService.instance.add(VinilosApiService.getBands(
            { response ->
                Log.d("GET BANDS", "response: $response")

                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    performersList.add(
                        Band(
                            id = item.getInt("id"),
                            name = item.getString("name"),
                            image = item.getString("image"),
                            description = item.getString("description"),
                            creationDate = item.getString("creationDate")
                        )
                    )
                }

            },
            {
                Log.d("GET BANDS", "error: $it")
                onError(it)
            }
        ))

        vinilosApiService.instance.add(VinilosApiService.getMusicians(
            { response ->
                Log.d("GET MUSICIANS", "response: $response")

                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    performersList.add(
                        Musician(
                            id = item.getInt("id"),
                            name = item.getString("name"),
                            image = item.getString("image"),
                            description = item.getString("description"),
                            birthDate = item.getString("birthDate")
                        )
                    )
                }

            },
            {
                Log.d("GET MUSICIANS", "error: $it")
                onError(it)
            }
        ))

        onComplete(performersList)
    }
}