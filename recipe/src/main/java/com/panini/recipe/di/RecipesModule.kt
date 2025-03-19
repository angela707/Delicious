package com.panini.recipe.di

import com.panini.core.data.database.DatabaseFactory
import com.panini.recipe.domain.LocalRecipeDataSource
import com.panini.recipe.data.RecipeRepositoryImpl
import com.panini.recipe.data.local.LocalRecipeDataSourceImpl
import com.panini.recipe.data.local.RecipeDatabase
import com.panini.recipe.data.remote.RemoteRecipeDataSourceImpl
import com.panini.recipe.domain.RecipeRepository
import com.panini.recipe.domain.RemoteRecipeDataSource

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val recipesModule = module {

    single<RecipeDatabase> {
        val databaseFactory: DatabaseFactory = get()
        databaseFactory.create("recipe.db", RecipeDatabase::class)
    }

    single { get<RecipeDatabase>().recipeDao }

    singleOf(::RecipeRepositoryImpl).bind<RecipeRepository>()
    singleOf(::RemoteRecipeDataSourceImpl).bind<RemoteRecipeDataSource>()
    singleOf(::LocalRecipeDataSourceImpl).bind<LocalRecipeDataSource>()
}