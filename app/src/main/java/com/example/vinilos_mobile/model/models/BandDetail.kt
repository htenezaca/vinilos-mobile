package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class BandDetail(
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    override val albums: Array<Album>,
    val creationDate: String
): PerformerDetail() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BandDetail

        if (id != other.id) return false
        if (name != other.name) return false
        if (image != other.image) return false
        if (description != other.description) return false
        if (!albums.contentEquals(other.albums)) return false
        if (creationDate != other.creationDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + albums.contentHashCode()
        result = 31 * result + creationDate.hashCode()
        return result
    }
}

fun deserializeBandDetail(json: JSONObject): BandDetail {
    return BandDetail(
        id = json.getInt("id"),
        name = json.getString("name"),
        image = json.getString("image"),
        description = json.getString("description"),
        albums = deserializeAlbums(json.getJSONArray("albums")),
        creationDate = json.getString("creationDate")
    )
}
