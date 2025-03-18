package com.panini.core.di

import androidx.room.Room
import com.panini.core.data.database.RecipeDatabase
import com.panini.core.data.networking.HttpClientFactory
import com.panini.core.data.remote.RecipeRepositoryImpl
import com.panini.core.domain.RecipeRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            RecipeDatabase::class.java,
            "recipe.db"
        ).build()
    }

    single { get<RecipeDatabase>().recipeDao}


    singleOf(::RecipeRepositoryImpl).bind<RecipeRepository>()
}