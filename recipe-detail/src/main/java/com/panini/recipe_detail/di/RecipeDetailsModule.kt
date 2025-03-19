package com.panini.recipe_detail.di

import com.panini.recipe_detail.presentation.RecipeDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val recipeDetailsModule = module {
    viewModelOf(::RecipeDetailsViewModel)
}