package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class Musician(
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    val birthDate: String
): Performer()

fun deserializeMusician(json: JSONObject): Musician {
    return Musician(
        id = json.getInt("id"),
        name = json.getString("name"),
        image = json.getString("image"),
        description = json.getString("description"),
        birthDate = json.getString("birthDate")
    )
}