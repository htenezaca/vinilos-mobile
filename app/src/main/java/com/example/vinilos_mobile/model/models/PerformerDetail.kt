@file:Suppress("RemoveRedundantQualifierName")

package com.example.vinilos_mobile.model.models

abstract class PerformerDetail {
    abstract val id: Int
    abstract val name: String
    abstract val image: String
    abstract val description: String
    abstract val albums: List<Album>
}

