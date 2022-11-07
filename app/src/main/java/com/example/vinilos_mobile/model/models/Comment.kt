package com.example.vinilos_mobile.model.models

import org.json.JSONArray
import org.json.JSONObject

data class Comment(
    val id: Int,
    val description: String,
    val rating: String,
)

fun deserializeComments(json: JSONArray): List<Comment> {
    val comments = mutableListOf<Comment>()
    for (i in 0 until json.length()) {
        val jsonComment = json.getJSONObject(i)
        val comment = deserializeComment(jsonComment)
        comments.add(comment)
    }
    return comments
}

fun deserializeComment(json: JSONObject): Comment {
    return Comment(
        id = json.getInt("id"),
        description = json.getString("description"),
        rating = json.getString("rating"),
    )
}