package com.example.vinilos_mobile.model.models

data class Band(
    override val id: Int,
    override val name: String,
    override val image: String,
    override val description: String,
    val creationDate: String
): Performer()