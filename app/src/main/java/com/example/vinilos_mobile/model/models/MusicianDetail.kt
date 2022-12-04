package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class MusicianDetail(
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    override val albums: Array<Album>,
    val birthDate: String
): PerformerDetail() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MusicianDetail

        if (id != other.id) return false
        if (name != other.name) return false
        if (image != other.image) return false
        if (description != other.description) return false
        if (!albums.contentEquals(other.albums)) return false
        if (birthDate != other.birthDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + albums.contentHashCode()
        result = 31 * result + birthDate.hashCode()
        return result
    }
}

fun deserializeMusicianDetail(json: JSONObject): MusicianDetail {
    return MusicianDetail(
        id = json.getInt("id"),
        name = json.getString("name"),
        image = json.getString("image"),
        description = json.getString("description"),
        albums = deserializeAlbums(json.getJSONArray("albums")),
        birthDate = json.getString("birthDate")
    )
}