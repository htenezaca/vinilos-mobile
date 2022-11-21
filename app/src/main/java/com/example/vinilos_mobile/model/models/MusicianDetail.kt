package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class MusicianDetail(
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    override val albums: Array<Album>,
    val birthDate: String
): PerformerDetail()

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