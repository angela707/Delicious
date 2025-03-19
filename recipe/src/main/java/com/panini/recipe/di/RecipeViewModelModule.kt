package com.panini.recipe.di

import com.panini.recipe.presentation.recipe_details.RecipeDetailsViewModel
import com.panini.recipe.presentation.recipe_list.RecipeListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val recipeViewModelModule = module {
    viewModelOf(::RecipeListViewModel)
    viewModelOf(::RecipeDetailsViewModel)
}