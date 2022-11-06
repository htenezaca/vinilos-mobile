package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class Band(
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    val creationDate: String
): Performer()

fun deserializeBand(json: JSONObject): Band {
    return Band(
        id = json.getInt("id"),
        name = json.getString("name"),
        image = json.getString("image"),
        description = json.getString("description"),
        creationDate = json.getString("creationDate")
    )
}
