package com.example.vinilos_mobile.model.api

import android.content.Context
import android.util.LruCache
import com.example.vinilos_mobile.model.models.Album
import com.example.vinilos_mobile.model.models.AlbumDetail

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

    private var performers: LruCache<Int, AlbumDetail> = LruCache(5)

    fun getAlbum(albumId: Int): AlbumDetail?{
        return if (albums[albumId] != null) albums[albumId]!! else null
    }
    fun addAlbum(albumId: Int, albumDetail: AlbumDetail){
        if(albums[albumId] == null){
            albums.put(albumId, albumDetail)
        }
    }
}