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


    suspend fun getCollectors(): List<Collector> {
        return VinilosApiService.getInstance(applicationContext).getCollectors()
    }

    suspend fun getCollector(collectorId: Int): CollectorDetail {
        return VinilosApiService.getInstance(applicationContext).getCollector(collectorId)
    }

    suspend fun getAlbums(): List<Album> {
        return VinilosApiService.getInstance(applicationContext).getAlbums()
    }

    suspend fun getAlbum(albumId: Int): AlbumDetail {
        return VinilosApiService.getInstance(applicationContext).getAlbumDetail(albumId)
    }

    suspend fun getPerformers(): List<Performer> {
        val performersList = mutableListOf<Performer>()

        val bands = VinilosApiService.getInstance(applicationContext).getBands()
        val musicians = VinilosApiService.getInstance(applicationContext).getMusicians()

        performersList.addAll(bands)
        performersList.addAll(musicians)

        return performersList
    }
}
