package com.panini.recipe.presentation.recipe_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.panini.core.R
import com.panini.core.presentation.components.TitleText
import com.panini.recipe.domain.Recipe
import com.panini.recipe.presentation.components.RecipeCard
import com.panini.recipe.presentation.preview_providers.RecipesPreviewProvider
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
        RecipesListScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 24.dp,
                    start = 24.dp,
                    end = 24.dp
                ),
            state = state.value,
            onClick = onClick
        )

    }
}

@Composable
private fun RecipesListScreen(
    modifier: Modifier = Modifier,
    state: RecipeListUiState,
    onClick: (Int) -> Unit
) {
    when {
        state.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.error)
            }
        }

        else -> {
            RecipeScreenInternal(
                modifier = modifier,
                recipes = state.recipes,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun RecipeScreenInternal(
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