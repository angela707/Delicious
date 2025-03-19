package com.panini.core.domain

import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.EmptyResult
import com.panini.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun fetchRecipes(): EmptyResult<DataError>
    suspend fun getRecipeById(id: Int): Result<Recipe, DataError.Local>
}