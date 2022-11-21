package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class BandDetail(
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    override val albums: Array<Album>,
    val creationDate: String
): PerformerDetail()

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
