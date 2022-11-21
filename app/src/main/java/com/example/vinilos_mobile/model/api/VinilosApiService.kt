package com.example.vinilos_mobile.model.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos_mobile.model.models.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class VinilosApiService constructor(context: Context) {

    companion object {
        private const val BASE_URL = "https://vinilos-backend.herokuapp.com/"
        private const val COLLECTORS_PATH = "collectors"
        private const val ALBUMS_PATH = "albums"
        private const val BANDS_PATH = "bands"
        private const val MUSICIANS_PATH = "musicians"

<<<<<<< HEAD
<<<<<<< HEAD
        private var instance: VinilosApiService? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: VinilosApiService(context).also {
                instance = it
            }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums() = suspendCoroutine<Array<Album>> { cont ->
        requestQueue.add(
            getRequest(ALBUMS_PATH, { response ->
                val resp = JSONArray(response)

                val albumsList = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    albumsList.add(
                        deserializeAlbum(item)
                    )
                }
                cont.resume(albumsList.toTypedArray())

            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getCollectors() = suspendCoroutine<Array<Collector>> { cont ->
        requestQueue.add(
            getRequest(COLLECTORS_PATH, { response ->
                val resp = JSONArray(response)

                val collectorsList = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    collectorsList.add(
                        deserializeCollector(item)
                    )
                }
                cont.resume(collectorsList.toTypedArray())

            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getBands() = suspendCoroutine<Array<Band>> { cont ->
        requestQueue.add(
            getRequest(BANDS_PATH, { response ->
                val resp = JSONArray(response)

                val bandsList = mutableListOf<Band>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    bandsList.add(
                        deserializeBand(item)
                    )
                }
                cont.resume(bandsList.toTypedArray())

            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getMusicians() = suspendCoroutine<Array<Musician>> { cont ->
        requestQueue.add(
            getRequest(MUSICIANS_PATH, { response ->
                val resp = JSONArray(response)

                val musiciansList = mutableListOf<Musician>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    musiciansList.add(
                        deserializeMusician(item)
                    )
                }
                cont.resume(musiciansList.toTypedArray())

            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getAlbumDetail(albumId: Int) = suspendCoroutine { cont ->
        requestQueue.add(
            getRequest("$ALBUMS_PATH/$albumId", { response ->
                val resp = JSONObject(response)

                val album = deserializeAlbumDetail(resp)

                cont.resume(album)
            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getCollector(collectorId: Int) = suspendCoroutine { cont ->
        requestQueue.add(
            getRequest("$COLLECTORS_PATH/$collectorId", { response ->
                val resp = JSONObject(response)

                val collector = deserializeCollectorDetail(resp)

                cont.resume(collector)
            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getMusicianDetail(musicianId: Int) = suspendCoroutine<PerformerDetail> { cont ->
        requestQueue.add(
            getRequest("$MUSICIANS_PATH/$musicianId", { response ->
                val resp = JSONObject(response)

                val musician = deserializeMusicianDetail(resp)

                cont.resume(musician)
            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getBandDetail(bandId: Int) = suspendCoroutine<PerformerDetail> { cont ->
        requestQueue.add(
            getRequest("$BANDS_PATH/$bandId", { response ->
                val resp = JSONObject(response)

                val musician = deserializeBandDetail(resp)

                cont.resume(musician)
            }, {
                cont.resumeWithException(it)
            })
        )
    }

    private fun getRequest(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }
}


=======
        fun getCollectors(
            responseListener: Response.Listener<JSONArray>,
            errorListener: Response.ErrorListener
        ): JsonArrayRequest {
            return JsonArrayRequest(BASE_URL + COLLECTORS_PATH, responseListener, errorListener)
        }
=======
        private var instance: VinilosApiService? = null
>>>>>>> 627a67c (Release/sprint2 (#82))

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: VinilosApiService(context).also {
                instance = it
            }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums() = suspendCoroutine<Array<Album>> { cont ->
        requestQueue.add(
            getRequest(ALBUMS_PATH, { response ->
                val resp = JSONArray(response)

                val albumsList = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    albumsList.add(
                        deserializeAlbum(item)
                    )
                }
                cont.resume(albumsList.toTypedArray())

            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getCollectors() = suspendCoroutine<Array<Collector>> { cont ->
        requestQueue.add(
            getRequest(COLLECTORS_PATH, { response ->
                val resp = JSONArray(response)

                val collectorsList = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    collectorsList.add(
                        deserializeCollector(item)
                    )
                }
                cont.resume(collectorsList.toTypedArray())

            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getBands() = suspendCoroutine<Array<Band>> { cont ->
        requestQueue.add(
            getRequest(BANDS_PATH, { response ->
                val resp = JSONArray(response)

                val bandsList = mutableListOf<Band>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    bandsList.add(
                        deserializeBand(item)
                    )
                }
                cont.resume(bandsList.toTypedArray())

            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getMusicians() = suspendCoroutine<Array<Musician>> { cont ->
        requestQueue.add(
            getRequest(MUSICIANS_PATH, { response ->
                val resp = JSONArray(response)

                val musiciansList = mutableListOf<Musician>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    musiciansList.add(
                        deserializeMusician(item)
                    )
                }
                cont.resume(musiciansList.toTypedArray())

            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getAlbumDetail(albumId: Int) = suspendCoroutine { cont ->
        requestQueue.add(
            getRequest("$ALBUMS_PATH/$albumId", { response ->
                val resp = JSONObject(response)

                val album = deserializeAlbumDetail(resp)

                cont.resume(album)
            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getCollector(collectorId: Int) = suspendCoroutine { cont ->
        requestQueue.add(
            getRequest("$COLLECTORS_PATH/$collectorId", { response ->
                val resp = JSONObject(response)

                val collector = deserializeCollectorDetail(resp)

                cont.resume(collector)
            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getMusicianDetail(musicianId: Int) = suspendCoroutine<PerformerDetail> { cont ->
        requestQueue.add(
            getRequest("$MUSICIANS_PATH/$musicianId", { response ->
                val resp = JSONObject(response)

                val musician = deserializeMusicianDetail(resp)

                cont.resume(musician)
            }, {
                cont.resumeWithException(it)
            })
        )
    }

    suspend fun getBandDetail(bandId: Int) = suspendCoroutine<PerformerDetail> { cont ->
        requestQueue.add(
            getRequest("$BANDS_PATH/$bandId", { response ->
                val resp = JSONObject(response)

                val musician = deserializeBandDetail(resp)

                cont.resume(musician)
            }, {
                cont.resumeWithException(it)
            })
        )
    }

    private fun getRequest(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }
}
<<<<<<< HEAD
>>>>>>> 95f5312 (Release/sprint1 (#63))
=======


>>>>>>> 627a67c (Release/sprint2 (#82))
