package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class AlbumDetail(
    val albumId: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val tracks: List<Track>,
    val performers: List<Performer>,
    val comments: List<Comment>,
)

fun deserializeAlbumDetail(json: JSONObject): AlbumDetail {
    return AlbumDetail(
        albumId = json.getInt("id"),
        name = json.getString("name"),
        cover = json.getString("cover"),
        releaseDate = json.getString("releaseDate"),
        description = json.getString("description"),
        genre = json.getString("genre"),
        recordLabel = json.getString("recordLabel"),
        tracks = deserializeTracks(json.getJSONArray("tracks")),
        performers = deserializePerformers(json.getJSONArray("performers")),
        comments = deserializeComments(json.getJSONArray("comments")),
    )
}