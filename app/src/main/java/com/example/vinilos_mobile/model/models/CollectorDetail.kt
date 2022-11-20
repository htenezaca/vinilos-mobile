package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class CollectorDetail(
    val collectorId: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: Array<Comment>,
    val favoritePerformers: Array<Performer>,
    val collectorAlbums: Array<CollectorAlbum>
)

fun deserializeCollectorDetail(json: JSONObject): CollectorDetail {
    val collector = deserializeCollector(json)
    return CollectorDetail(
        collectorId = collector.collectorId,
        name = collector.name,
        telephone = collector.telephone,
        email = collector.email,
        comments = deserializeComments(json.getJSONArray("comments")),
        favoritePerformers = deserializePerformers(json.getJSONArray("favoritePerformers")),
        collectorAlbums = deserializeCollectorAlbums(json.getJSONArray("collectorAlbums"))
    )
}