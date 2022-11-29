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

fun deserializeAlbums(json: JSONArray): Array<Album> {
    val albums = mutableListOf<Album>()
    for (i in 0 until json.length()) {
        albums.add(deserializeAlbum(json.getJSONObject(i)))
    }
    return albums.toTypedArray()
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

fun serializeAlbum(album: Album): JSONObject {
    var json: JSONObject = JSONObject()

    json.put("name", album.name)
    json.put("cover", album.cover)
    json.put("releaseDate", album.releaseDate)
    json.put("description", album.description)
    json.put("genre", album.genre)
    json.put("recordLabel", album.recordLabel)

    return json
}