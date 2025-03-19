package com.panini.recipe.presentation.recipe_list

import androidx.compose.runtime.Stable
import com.panini.recipe.domain.Recipe

@Stable
data class RecipeListUiState(
    val recipes: List<Recipe> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = true
)