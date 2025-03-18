package com.panini.recipe_list.data.local

import android.database.sqlite.SQLiteFullException
import com.panini.core.data.database.RecipeDao
import com.panini.core.domain.LocalRecipeDataSource
import com.panini.core.domain.Recipe
import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.Result
import com.panini.recipe_list.data.utils.toRecipe
import com.panini.recipe_list.data.utils.toRecipeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalRecipeDataSourceImpl(
    private val recipeDao: RecipeDao
) : LocalRecipeDataSource {

    override fun getRecipes(): Flow<List<Recipe>> {
        return recipeDao.getRecipes()
            .map { recipeEntities ->
                recipeEntities.map { it.toRecipe() }
            }
    }

    override suspend fun upsertRecipes(recipes: List<Recipe>): Result<List<Int>, DataError.Local> {
        return try {
            val entities = recipes.map { it.toRecipeEntity() }
            recipeDao.upsertRecipes(entities)
            Result.Success(entities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }
}