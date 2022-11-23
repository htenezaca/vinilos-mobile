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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CollectorDetail

        if (collectorId != other.collectorId) return false
        if (name != other.name) return false
        if (telephone != other.telephone) return false
        if (email != other.email) return false
        if (!comments.contentEquals(other.comments)) return false
        if (!favoritePerformers.contentEquals(other.favoritePerformers)) return false
        if (!collectorAlbums.contentEquals(other.collectorAlbums)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = collectorId
        result = 31 * result + name.hashCode()
        result = 31 * result + telephone.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + comments.contentHashCode()
        result = 31 * result + favoritePerformers.contentHashCode()
        result = 31 * result + collectorAlbums.contentHashCode()
        return result
    }
}

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