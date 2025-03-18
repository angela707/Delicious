package com.panini.recipe_list.data.network

import kotlinx.serialization.Serializable

@Serializable
class RecipeResponseDto(
    val count: Int,
    val results: List<RecipeDto>
)