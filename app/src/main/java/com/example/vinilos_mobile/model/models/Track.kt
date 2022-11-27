package com.example.vinilos_mobile.model.models

import org.json.JSONArray
import org.json.JSONObject

data class Track (
    val id: Int,
    val name: String,
    val duration: String,
)

fun deserializeTracks(json: JSONArray): Array<Track> {
    val tracks = mutableListOf<Track>()
    for (i in 0 until json.length()) {
        val jsonTrack = json.getJSONObject(i)
        val track = deserializeTrack(jsonTrack)
        tracks.add(track)
    }
    // Switch mutable List to more efficient data structure
    return tracks.toTypedArray()
}

fun deserializeTrack(json: JSONObject): Track {
    return Track(
        id = json.getInt("id"),
        name = json.getString("name"),
        duration = json.getString("duration"),
    )
}

fun serializeTrack(track: Track): JSONObject {
    val json = JSONObject()
    json.put("name", track.name)
    json.put("duration", track.duration)
    return json
}