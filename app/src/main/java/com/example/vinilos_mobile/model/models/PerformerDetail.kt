package com.example.vinilos_mobile.model.models

import org.json.JSONObject

abstract class PerformerDetail {
    abstract val id: Int
    abstract val name: String
    abstract val image: String
    abstract val description: String
    abstract val albums: List<Album>
}

fun deserializePerformerDetail(json: org.json.JSONObject): PerformerDetail {
    return when (json.optString("birthDate")) {
        "" -> deserializeBandDetail(json)
        else -> deserializeMusicianDetail(json)
    }
}