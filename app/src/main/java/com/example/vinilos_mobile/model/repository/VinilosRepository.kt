package com.example.vinilos_mobile.model.repository

import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_mobile.model.api.VinilosApiService
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.model.models.Collector

class VinilosRepository {

    fun getCollectors(
        applicationContext: Context,
        onComplete: (resp: List<Collector>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {

        var vinilosApiService = VinilosApiService(applicationContext)

        vinilosApiService.instance.add(VinilosApiService.getCollectors(
            { response ->
                Log.d("TAG1", response.toString())
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
                Log.d("TAG2", it.toString())
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
                    Log.d("TAG1", response.toString())
                    val albumsList = mutableListOf<Album>()

                    for (i in 0 until response.length()) {
                        val item = response.getJSONObject(i)
                        albumsList.add(
                            Album(
                                albumId= item.getInt("id"),
                                name= item.getString("name"),
                                cover= item.getString("cover"),
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
                    Log.d("TAG2", it.toString())
                    onError(it)
                }
            ))

    }
}