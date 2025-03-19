package com.panini.recipe.presentation.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panini.core.domain.util.Result
import com.panini.recipe.domain.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeListViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private var _state = MutableStateFlow(RecipeListUiState())
    var state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(isLoading = true)
        }

        getRecipes()
        viewModelScope.launch {
            when (recipeRepository.fetchRecipes()) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            error = "An error occurred.",
                            isLoading = false
                        )
                    }
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            error = null,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun getRecipes() = viewModelScope.launch {
        recipeRepository.getRecipes().collect { recipeList ->
            _state.update {
                it.copy(
                    recipes = recipeList,
                    error = null,
                    isLoading = false
                )
            }
        }
    }
}