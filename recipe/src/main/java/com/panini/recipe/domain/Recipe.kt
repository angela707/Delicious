package com.panini.recipe.domain

data class Recipe(
    val id: Int,
    val name: String,
    val description: String?,
    val prepTimeMinutes: Int?,
    val thumbnailUrl: String?,
    val videoUrl: String?,
    val yields: String?
)