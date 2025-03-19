package com.panini.core.domain

import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface LocalRecipeDataSource {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun getRecipeById(id: Int): Result<Recipe, DataError.Local>
    suspend fun upsertRecipes(recipes: List<Recipe>): Result<List<Int>, DataError.Local>
}