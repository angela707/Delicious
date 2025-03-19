package com.panini.recipe.domain

import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.Result

interface RemoteRecipeDataSource {
    suspend fun getRecipes(): Result<List<Recipe>, DataError.Network>
}