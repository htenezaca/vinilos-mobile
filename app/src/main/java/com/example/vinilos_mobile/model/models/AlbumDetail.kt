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
    val tracks: Array<Track>,
    val performers: Array<Performer>,
    val comments: Array<Comment>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AlbumDetail

        if (albumId != other.albumId) return false
        if (name != other.name) return false
        if (cover != other.cover) return false
        if (releaseDate != other.releaseDate) return false
        if (description != other.description) return false
        if (genre != other.genre) return false
        if (recordLabel != other.recordLabel) return false
        if (!tracks.contentEquals(other.tracks)) return false
        if (!performers.contentEquals(other.performers)) return false
        if (!comments.contentEquals(other.comments)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = albumId
        result = 31 * result + name.hashCode()
        result = 31 * result + cover.hashCode()
        result = 31 * result + releaseDate.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + genre.hashCode()
        result = 31 * result + recordLabel.hashCode()
        result = 31 * result + tracks.contentHashCode()
        result = 31 * result + performers.contentHashCode()
        result = 31 * result + comments.contentHashCode()
        return result
    }
}

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