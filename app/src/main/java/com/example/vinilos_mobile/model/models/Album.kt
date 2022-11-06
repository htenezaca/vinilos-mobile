package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class Album(
    val albumId: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String
)

fun deserializeAlbum(json: JSONObject): Album {
    return Album(
        albumId = json.getInt("id"),
        name = json.getString("name"),
        cover = json.getString("cover"),
        releaseDate = json.getString("releaseDate"),
        description = json.getString("description"),
        genre = json.getString("genre"),
        recordLabel = json.getString("recordLabel")
    )
}