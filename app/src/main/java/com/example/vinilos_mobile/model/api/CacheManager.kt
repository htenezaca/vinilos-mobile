package com.example.vinilos_mobile.model.api

import android.content.Context
import android.util.LruCache
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.model.models.AlbumDetail
import com.example.vinilos_mobile.model.models.CollectorDetail
import com.example.vinilos_mobile.model.models.PerformerDetail

class CacheManager(context: Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

    private var albums: LruCache<Int, AlbumDetail> = LruCache(5)

    fun getAlbum(albumId: Int): AlbumDetail?{
        return if (albums[albumId] != null) albums[albumId]!! else null
    }
    fun addAlbum(albumId: Int, albumDetail: AlbumDetail){
        if(albums[albumId] == null){
            albums.put(albumId, albumDetail)
        }
    }

    private var performers: LruCache<Int, PerformerDetail> = LruCache(5)

    fun getPerformer(performerId: Int): PerformerDetail?{
        return if (performers[performerId] != null) performers[performerId]!! else null
    }
    fun addPerformer(performerId: Int, performerDetail: PerformerDetail){
        if(performers[performerId] == null){
            performers.put(performerId, performerDetail)
        }
    }

    private var collectors: LruCache<Int, CollectorDetail> = LruCache(5)

    fun getCollector(collectorId: Int): CollectorDetail?{
        return if (collectors[collectorId] != null) collectors[collectorId]!! else null
    }
    fun addCollector(collectorId: Int, collectorDetail: CollectorDetail){
        if(collectors[collectorId] == null){
            collectors.put(collectorId, collectorDetail)
        }
    }
}