package com.panini.recipe.data

import com.panini.recipe.domain.LocalRecipeDataSource
import com.panini.recipe.domain.Recipe
import com.panini.recipe.domain.RecipeRepository
import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.EmptyResult
import com.panini.core.domain.util.Result
import com.panini.core.domain.util.asEmptyDataResult
import com.panini.recipe.domain.RemoteRecipeDataSource
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

    override suspend fun getRecipeById(id: Int): Result<Recipe, DataError.Local> {
        return localRecipeDataSource.getRecipeById(id)
    }
}