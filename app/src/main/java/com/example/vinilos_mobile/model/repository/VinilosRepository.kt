package com.example.vinilos_mobile.model.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.VolleyError
import com.example.vinilos_mobile.model.api.CacheManager
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
        var cacheResp = CacheManager.getInstance(applicationContext).getAlbum(albumId)
        return if(cacheResp == null) {
            Log.d("getAlbum decision", "from API")
            val albumDetail =
                VinilosApiService.getInstance(applicationContext).getAlbumDetail(albumId)
            CacheManager.getInstance(applicationContext).addAlbum(albumId, albumDetail)
            albumDetail
        } else {
            Log.d("getAlbum decision", "from cache")
            cacheResp
        }
    }

    suspend fun getPerformers(): List<Performer> {
        val performersList = mutableListOf<Performer>()

        val bands = VinilosApiService.getInstance(applicationContext).getBands()
        val musicians = VinilosApiService.getInstance(applicationContext).getMusicians()

        performersList.addAll(bands)
        performersList.addAll(musicians)

        return performersList
    }

    suspend fun getMusician(musicianId: Int): MusicianDetail {
        return VinilosApiService.getInstance(applicationContext).getMusicianDetail(musicianId)
    }

}
