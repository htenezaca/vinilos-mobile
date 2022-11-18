package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class MusicianDetail(
    val musicianId: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<Album>
)

fun deserializeMusicianDetail(json: JSONObject): MusicianDetail {
    return MusicianDetail(
        musicianId = json.getInt("id"),
        name = json.getString("name"),
        image = json.getString("image"),
        description = json.getString("description"),
        birthDate = json.getString("birthDate"),
        albums = deserializeAlbums(json.getJSONArray("albums"))
    )
}