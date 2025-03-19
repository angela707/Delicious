package com.panini.recipe.presentation.recipe_details

import androidx.compose.runtime.Stable
import com.panini.recipe.domain.Recipe

@Stable
data class RecipeDetailsUiState(
    val recipe: Recipe? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)