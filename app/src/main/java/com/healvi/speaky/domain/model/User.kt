package com.healvi.speaky.domain.model

data class User(
    val name: String,
    val username: String,
    val email: String,
    val imgPhoto: String,
    val level: String,
    val status: Boolean,
    val score: Int
)
