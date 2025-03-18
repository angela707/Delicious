package com.panini.core.domain

import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipes(): Flow<List<Recipe>>
    suspend fun fetchRecipes(): EmptyResult<DataError>
}