package com.panini.core.data.remote

import kotlinx.serialization.Serializable

@Serializable
class RecipeResponseDto(
    val count: Int,
    val results: List<RecipeDto>
)