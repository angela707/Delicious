package com.panini.recipe_list.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String?,
    @SerialName("prep_time_minutes") val prepTimeMinutes: Int? = null,
    @SerialName("thumbnail_url") val thumbnailUrl: String? = null,
    @SerialName("video_url") val videoUrl: String? = null,
    @SerialName("yields") val yields: String?
)