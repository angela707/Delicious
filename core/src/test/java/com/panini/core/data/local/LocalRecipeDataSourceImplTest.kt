package com.panini.core.data.local

import com.panini.core.data.AppBaseTest
import com.panini.core.data.database.LocalRecipeDataSourceImpl
import com.panini.core.data.database.RecipeDao
import com.panini.core.data.database.RecipeEntity
import com.panini.core.data.util.toRecipe
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

class LocalRecipeDataSourceImplTest : AppBaseTest() {

    private lateinit var localRecipeDataSourceImpl: LocalRecipeDataSourceImpl
    private val recipeDaoMock = mockk<RecipeDao>(relaxed = true)

    private val fakeRecipesEntities = listOf(
        RecipeEntity(
            id = 0,
            recipeId = 234,
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
        localRecipeDataSourceImpl = LocalRecipeDataSourceImpl(
            recipeDao = recipeDaoMock
        )
    }

    @Test
    fun `getRecipes should return flow from recipeDao`() = runTest {
        coEvery { recipeDaoMock.getRecipes() } returns flowOf(fakeRecipesEntities)

        val result = localRecipeDataSourceImpl.getRecipes()

        assertEquals(fakeRecipesEntities.map { it.toRecipe() }, result.first())
        coVerify { recipeDaoMock.getRecipes() }
    }

    @Test
    fun `upsertRecipes should insert recipes and return success`() = runTest {
        val recipes = fakeRecipesEntities.map { it.toRecipe() }
        val result = localRecipeDataSourceImpl.upsertRecipes(recipes)

        assertTrue(result is Result.Success)
        coVerify { recipeDaoMock.upsertRecipes(fakeRecipesEntities) }
    }
}