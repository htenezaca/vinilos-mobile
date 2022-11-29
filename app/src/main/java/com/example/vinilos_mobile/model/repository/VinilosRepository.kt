package com.example.vinilos_mobile.model.repository

import android.app.Application
import android.util.Log
import com.example.vinilos_mobile.model.api.CacheManager
import com.example.vinilos_mobile.model.api.VinilosApiService
import com.example.vinilos_mobile.model.models.*

class VinilosRepository(private val applicationContext: Application) {


    suspend fun getCollectors(): Array<Collector> {
        return VinilosApiService.getInstance(applicationContext).getCollectors()
    }

    suspend fun getCollector(collectorId: Int): CollectorDetail {
        val cacheResp = CacheManager.getInstance(applicationContext).getCollector(collectorId)
        return if (cacheResp == null) {
            Log.d("getCollector decision", "from API")
            val collectorDetail =
                VinilosApiService.getInstance(applicationContext).getCollector(collectorId)
            CacheManager.getInstance(applicationContext).addCollector(collectorId, collectorDetail)
            collectorDetail
        } else {
            Log.d("getCollector decision", "from cache")
            cacheResp
        }
    }

    suspend fun getAlbums(): Array<Album> {
        return VinilosApiService.getInstance(applicationContext).getAlbums()
    }

    suspend fun getAlbum(albumId: Int): AlbumDetail {
        val cacheResp = CacheManager.getInstance(applicationContext).getAlbum(albumId)
        return if (cacheResp == null) {
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

    suspend fun postAlbum(newAlbum: Album): Album {
        val albumJson = serializeAlbum(newAlbum)
        return VinilosApiService.getInstance(applicationContext).postAlbum(albumJson)
    }

    suspend fun getPerformers(): Array<Performer> {
        val performersList = mutableListOf<Performer>()

        val bands = VinilosApiService.getInstance(applicationContext).getBands()
        val musicians = VinilosApiService.getInstance(applicationContext).getMusicians()

        performersList.addAll(bands)
        performersList.addAll(musicians)

        return performersList.toTypedArray()
    }

    suspend fun getPerformer(performerId: Int, performerType: PerformerType): PerformerDetail {
        val cacheResp = CacheManager.getInstance(applicationContext).getPerformer(performerId)
        if (cacheResp == null) {
            Log.d("getPerformer decision", "from API")
            val apiResp: PerformerDetail = if (performerType == PerformerType.BAND) {
                VinilosApiService.getInstance(applicationContext)
                    .getBandDetail(performerId)
            } else {
                VinilosApiService.getInstance(applicationContext)
                    .getMusicianDetail(performerId)
            }
            CacheManager.getInstance(applicationContext).addPerformer(performerId, apiResp)
            return apiResp
        } else {
            Log.d("getAlbum decision", "from cache")
            return cacheResp
        }
    }

}
