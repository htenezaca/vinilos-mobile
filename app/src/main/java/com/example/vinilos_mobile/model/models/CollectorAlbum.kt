package com.example.vinilos_mobile.model.models

import org.json.JSONArray
import org.json.JSONObject

data class CollectorAlbum(
    val id: Int,
    val price: Float,
    val status: String,
)

fun deserializeCollectorAlbums(json: JSONArray): List<CollectorAlbum> {
    val collectorAlbums = mutableListOf<CollectorAlbum>()
    for (i in 0 until json.length()) {
        val jsonCollectorAlbum = json.getJSONObject(i)
        val collectorAlbum = deserializeCollectorAlbum(jsonCollectorAlbum)
        collectorAlbums.add(collectorAlbum)
    }
    return collectorAlbums
}

fun deserializeCollectorAlbum(json: JSONObject): CollectorAlbum {
    return CollectorAlbum(
        id = json.getInt("id"),
        price = json.getDouble("price").toFloat(),
        status = json.getString("status"),
    )
}