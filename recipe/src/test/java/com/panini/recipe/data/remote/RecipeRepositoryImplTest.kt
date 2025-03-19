package com.panini.recipe.data.remote

import com.panini.recipe.data.AppBaseTest
import com.panini.recipe.domain.LocalRecipeDataSource
import com.panini.recipe.domain.Recipe
import com.panini.core.domain.util.DataError
import com.panini.core.domain.util.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RecipeRepositoryImplTest : AppBaseTest() {

    private lateinit var recipeRepositoryImpl: com.panini.recipe.data.RecipeRepositoryImpl
    private val localRecipeDataSource = mockk<LocalRecipeDataSource>(relaxed = true)
    private val remoteRecipeDataSource = mockk<com.panini.recipe.domain.RemoteRecipeDataSource>(relaxed = true)

    private val fakeRecipes = listOf(
        Recipe(
            id = 1,
            name = "Pancakes",
            description = "Good",
            prepTimeMinutes = 10,
            thumbnailUrl = "thumb",
            videoUrl = "video",
            yields = "2 servings"
        )
    )

    @BeforeEach
    fun setUp() {
        recipeRepositoryImpl = com.panini.recipe.data.RecipeRepositoryImpl(
            localRecipeDataSource = localRecipeDataSource,
            remoteRecipeDataSource = remoteRecipeDataSource,
            applicationScope = testScope
        )
    }

    @Test
    fun `getRecipes should return flow from local data source`() = runTest {
        coEvery { localRecipeDataSource.getRecipes() } returns flowOf(fakeRecipes)

        val result = recipeRepositoryImpl.getRecipes()

        assertEquals(fakeRecipes, result.first())
        coVerify { localRecipeDataSource.getRecipes() }
    }

    @Test
    fun `fetchRecipes should call upsertRecipes on success`() = runTest {
        val remoteSuccess = Result.Success(fakeRecipes)
        val upsertSuccess = Result.Success(listOf(1))

        coEvery { remoteRecipeDataSource.getRecipes() } returns remoteSuccess
        coEvery { localRecipeDataSource.upsertRecipes(fakeRecipes) } returns upsertSuccess

        val result = recipeRepositoryImpl.fetchRecipes()

        assertTrue(result is Result.Success)
        coVerify { remoteRecipeDataSource.getRecipes() }
        coVerify { localRecipeDataSource.upsertRecipes(fakeRecipes) }
    }

    @Test
    fun `fetchRecipes should return error when remote fails`() = runTest {
        val errorResult: Result<List<Recipe>, DataError.Network> =
            Result.Error(DataError.Network.NO_INTERNET)
        coEvery { remoteRecipeDataSource.getRecipes() } returns errorResult

        val result = recipeRepositoryImpl.fetchRecipes()

        assertTrue(result is Result.Error)
        coVerify { remoteRecipeDataSource.getRecipes() }
        coVerify(exactly = 0) { localRecipeDataSource.upsertRecipes(any()) }
    }
}