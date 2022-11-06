package com.example.vinilos_mobile.model.models

import org.json.JSONObject

data class Collector(
    val collectorId: Int,
    val name: String,
    val telephone: String,
    val email: String
)

fun deserializeCollector(json: JSONObject): Collector {
    return Collector(
        collectorId = json.getInt("id"),
        name = json.getString("name"),
        telephone = json.getString("telephone"),
        email = json.getString("email")
    )
}