package com.panini.recipe.data.util

import com.panini.recipe.domain.Recipe
import com.panini.recipe.data.local.RecipeEntity
import com.panini.recipe.data.remote.RecipeDto


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