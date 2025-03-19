package com.panini.recipe_list.di

import com.panini.recipe_list.presentation.RecipeListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val recipesModule = module {
    viewModelOf(::RecipeListViewModel)
}