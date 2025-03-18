package com.panini.recipe_list.di

import com.panini.core.domain.LocalRecipeDataSource
import com.panini.core.domain.RemoteRecipeDataSource
import com.panini.recipe_list.data.local.LocalRecipeDataSourceImpl
import com.panini.recipe_list.data.network.RemoteRecipeDataSourceImpl
import com.panini.recipe_list.presentation.RecipeListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val recipesModule = module {
    singleOf(::RemoteRecipeDataSourceImpl).bind<RemoteRecipeDataSource>()
    singleOf(::LocalRecipeDataSourceImpl).bind<LocalRecipeDataSource>()

    viewModelOf(::RecipeListViewModel)
}