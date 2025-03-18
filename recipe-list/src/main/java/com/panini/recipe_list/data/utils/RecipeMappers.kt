package com.panini.recipe_list.data.utils

import com.panini.core.data.database.RecipeEntity
import com.panini.core.domain.Recipe
import com.panini.recipe_list.data.network.RecipeDto

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