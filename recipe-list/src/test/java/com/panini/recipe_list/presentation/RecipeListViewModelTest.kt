package com.panini.recipe_list.presentation

import app.cash.turbine.test
import com.panini.core.domain.Recipe
import com.panini.core.domain.RecipeRepository
import com.panini.recipe_list.CoroutineProvider
import com.panini.recipe_list.CoroutineTest
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RecipeListViewModelTest : CoroutineTest {

    override lateinit var testCoroutineProvider: CoroutineProvider
    override lateinit var testScope: TestScope

    private lateinit var viewModel: RecipeListViewModel

    private val recipesList = listOf(
        Recipe(
            id = 1,
            name = "Pancakes",
            description = "Delicious",
            prepTimeMinutes = 10,
            thumbnailUrl = "thumb",
            videoUrl = "video",
            yields = "2 servings"
        ),
        Recipe(
            id = 2,
            name = "Waffles",
            description = "Yummy",
            prepTimeMinutes = 15,
            thumbnailUrl = "thumb2",
            videoUrl = "video2",
            yields = "4 servings"
        )
    )

    private val mockRepository = mockk<RecipeRepository>(relaxed = true) {
        every { getRecipes() } returns flowOf(recipesList)
    }

    @BeforeEach
    fun setUp() {
        viewModel = RecipeListViewModel(
            recipeRepository = mockRepository
        )
    }

    @Test
    fun `getRecipes should update state with recipes`() = testScope.runTest {
        viewModel.state.test {
            val updated = awaitItem()
            assertEquals(recipesList, updated.recipes)
        }

        coVerify { mockRepository.getRecipes() }
    }

    @Test
    fun `fetchRecipes should be called on init`() = testScope.runTest {
        coVerify { mockRepository.fetchRecipes() }
    }
}