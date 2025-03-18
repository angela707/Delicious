package com.panini.recipe_list.presentation

import com.panini.core.domain.Recipe

data class RecipeListUiState(
    val recipes: List<Recipe> = emptyList()
)