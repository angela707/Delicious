package com.panini.core.data.util

import com.panini.core.data.database.RecipeEntity
import com.panini.core.data.remote.RecipeDto
import com.panini.core.domain.Recipe


fun Recipe.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        recipeId = id,
        name = name,
        description = description,
        prepTimeMinutes = prepTimeMinutes,
        thumbnailUrl = thumbnailUrl,
        videoUrl = videoUrl,
        yields = yields
    )
}


fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = recipeId,
        name = name,
        description = description,
        prepTimeMinutes = prepTimeMinutes,
        thumbnailUrl = thumbnailUrl,
        videoUrl = videoUrl,
        yields = yields
    )
}

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = id,
        name = name,
        description = description,
        prepTimeMinutes = prepTimeMinutes,
        thumbnailUrl = thumbnailUrl,
        videoUrl = videoUrl,
        yields = yields
    )
}