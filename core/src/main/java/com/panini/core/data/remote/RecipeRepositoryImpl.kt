package com.panini.core.data.remote

import com.panini.core.domain.LocalRecipeDataSource
import com.panini.core.domain.Recipe
import com.panini.core.domain.RecipeRepository
import com.panini.core.domain.RemoteRecipeDataSource
import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.EmptyResult
import com.panini.core.domain.util.Result
import com.panini.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val localRecipeDataSource: LocalRecipeDataSource,
    private val remoteRecipeDataSource: RemoteRecipeDataSource,
    private val applicationScope: CoroutineScope,
) : RecipeRepository {

    override fun getRecipes(): Flow<List<Recipe>> {
        return localRecipeDataSource.getRecipes()
    }

    override suspend fun fetchRecipes(): EmptyResult<DataError> {
        return when (val result = remoteRecipeDataSource.getRecipes()) {
            is Result.Error -> result
            is Result.Success -> {
                applicationScope.async {
                    localRecipeDataSource.upsertRecipes(result.data).asEmptyDataResult()
                }.await()
            }
        }
    }
}