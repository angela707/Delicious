package com.panini.delicious.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object Recipe: Routes()

    @Serializable
    data object RecipeList : Routes()

    @Serializable
    data class RecipeDetails(val recipeId: Int) : Routes()
}