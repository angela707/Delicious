package com.panini.recipe_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panini.core.domain.RecipeRepository
import com.panini.core.domain.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class RecipeListViewModel(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private var _state = MutableStateFlow(RecipeListUiState())
    var state = _state.asStateFlow()

    init {
        getRecipes()
        viewModelScope.launch {
            when (val result = recipeRepository.fetchRecipes()) {
                is Result.Error -> {
                    Timber.d("error ${result.error}")
                }

                is Result.Success -> Unit
            }
        }
    }

    private fun getRecipes() = viewModelScope.launch {
        recipeRepository.getRecipes().collect { recipeList ->
            _state.update {
                it.copy(recipes = recipeList)
            }
        }
    }
}