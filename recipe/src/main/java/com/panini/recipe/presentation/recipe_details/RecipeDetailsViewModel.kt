package com.panini.recipe.presentation.recipe_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.panini.recipe.domain.RecipeRepository
import com.panini.core.domain.util.Result
import com.panini.core.presentation.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeDetailsUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<Routes.RecipeDetails>()
        val recipeId = args.recipeId

        _state.update {
            it.copy(
                isLoading = true
            )
        }
        fetchRecipeById(recipeId)
    }

    private fun fetchRecipeById(recipeId: Int) {
        viewModelScope.launch {
            when (val result = recipeRepository.getRecipeById(recipeId)) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            recipe = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            recipe = null,
                            isLoading = false,
                            error = "Recipe not found"
                        )
                    }
                }
            }
        }
    }
}