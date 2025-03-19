package com.panini.core.data.database

import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteFullException
import com.panini.core.data.util.toRecipe
import com.panini.core.data.util.toRecipeEntity
import com.panini.core.domain.LocalRecipeDataSource
import com.panini.core.domain.Recipe
import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.Result
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

    override suspend fun getRecipeById(id: Int): Result<Recipe, DataError.Local> {
        return try {
            val entity = recipeDao.getRecipeByRecipeId(id)
            if (entity != null) {
                Result.Success(entity.toRecipe())
            } else {
                Result.Error(DataError.Local.NOT_FOUND)
            }
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DB_ERROR)
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