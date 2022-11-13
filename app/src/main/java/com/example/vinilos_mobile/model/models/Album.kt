package com.example.vinilos_mobile.model.models

import org.json.JSONArray
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

fun deserializeAlbums(json: JSONArray): List<Album> {
    val albums = mutableListOf<Album>()
    for (i in 0 until json.length()) {
        albums.add(deserializeAlbum(json.getJSONObject(i)))
    }
    return albums
}

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