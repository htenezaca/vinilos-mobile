package com.example.vinilos_mobile.model.models

abstract class Performer {
    abstract val id: Int
    abstract val name: String
    abstract val image: String
    abstract val description: String
}

enum class PerformerType {
    BAND, MUSICIAN
}

fun deserializePerformer(json: org.json.JSONObject): Performer {
    return when (json.optString("birthDate")) {
        "" -> deserializeBand(json)
        else -> deserializeMusician(json)
    }
}

fun deserializePerformers(json: org.json.JSONArray): Array<Performer> {
    val list = mutableListOf<Performer>()
    for (i in 0 until json.length()) {
        list.add(deserializePerformer(json.getJSONObject(i)))
    }
    return list.toTypedArray()
}