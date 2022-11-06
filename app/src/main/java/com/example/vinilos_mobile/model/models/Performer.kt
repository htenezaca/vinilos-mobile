package com.example.vinilos_mobile.model.models

import org.json.JSONArray
import org.json.JSONObject

data class Performer(
    val id : Int,
    val name : String,
    val image : String,
    val description : String,
    val birthDate : String,
)

fun deserializePerformers(json: JSONArray): List<Performer> {
    val performers = mutableListOf<Performer>()
    for (i in 0 until json.length()) {
        val jsonPerformer = json.getJSONObject(i)
        val performer = deserializePerformer(jsonPerformer)
        performers.add(performer)
    }
    return performers
}

fun deserializePerformer(json: JSONObject): Performer {
    return Performer(
        id = json.getInt("id"),
        name = json.getString("name"),
        image = json.getString("image"),
        description = json.getString("description"),
        birthDate = json.optString("birthDate", "Unknown"),
    )
}
