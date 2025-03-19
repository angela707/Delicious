package com.panini.recipe.data.remote

import kotlinx.serialization.Serializable

@Serializable
class RecipeResponseDto(
    val count: Int,
    val results: List<RecipeDto>
)