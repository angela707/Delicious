package com.panini.core.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertRecipes(runs: List<RecipeEntity>)

    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE recipeId = :recipeId LIMIT 1")
    suspend fun getRecipeByRecipeId(recipeId: Int): RecipeEntity?

}