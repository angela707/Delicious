package com.panini.recipe_detail.presentation

import com.panini.core.domain.Recipe

data class RecipeDetailsUiState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)