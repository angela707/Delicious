package com.panini.recipe_list.data.network

import com.panini.core.data.networking.get
import com.panini.core.domain.Recipe
import com.panini.core.domain.RemoteRecipeDataSource
import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.Result
import com.panini.core.domain.util.map
import com.panini.recipe_list.data.utils.toRecipe
import io.ktor.client.HttpClient

class RemoteRecipeDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteRecipeDataSource {

    companion object {
        const val STARTING_INDEX = 0
        const val COUNT = 50
    }

    override suspend fun getRecipes(): Result<List<Recipe>, DataError.Network> {
        return httpClient.get<RecipeResponseDto>(
            route = "/recipes/list",
            queryParameters = mapOf(
                "from" to "$STARTING_INDEX",
                "size" to "$COUNT"
            )
        ).map { recipeDtos ->
            recipeDtos.results.map { it.toRecipe() }
        }
    }
}