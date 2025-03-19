package com.panini.recipe_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.panini.core.domain.Recipe
import com.panini.recipe_list.R
import com.panini.recipe_list.presentation.components.RecipeCard
import com.panini.recipe_list.presentation.components.TitleText
import com.panini.recipe_list.presentation.preview_providers.RecipesPreviewProvider
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = koinViewModel(),
    onClick: (Int) -> Unit = {},
) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->

        RecipeScreenInternal(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 24.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            recipes = state.value.recipes,
            onClick = onClick
        )
    }
}

@Composable
fun RecipeScreenInternal(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onClick: (Int) -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TitleText(
            primaryText = R.string.try_something,
            accentText = R.string.delicious
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipes) { recipe ->
                RecipeCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    name = recipe.name,
                    prepTimeMinutes = recipe.prepTimeMinutes,
                    thumbnailUrl = recipe.thumbnailUrl,
                    yields = recipe.yields,
                    onClick = { onClick(recipe.id) }
                )
            }
        }
    }
}


@Preview
@Composable
private fun RecipeScreenInternalPreview(
    @PreviewParameter(RecipesPreviewProvider::class) recipeList: List<Recipe>
) {
    RecipeScreenInternal(
        recipes = recipeList,
    ) { }
}