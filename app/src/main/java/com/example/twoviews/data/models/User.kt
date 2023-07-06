package com.example.twoviews.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val age: Int
)