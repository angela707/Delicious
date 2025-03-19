package com.panini.recipe_list.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.panini.core.domain.Recipe
import com.panini.core.presentation.theme.DeliciousTheme
import com.panini.core.presentation.theme.fontFamily
import com.panini.core.presentation.theme.leagueScript
import com.panini.recipe_list.R
import com.panini.recipe_list.presentation.components.RecipeCard
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
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            TitleText(
                primaryText = R.string.try_something,
                accentText = R.string.delicious
            )
        }
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

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    @StringRes primaryText: Int,
    @StringRes accentText: Int,
) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 0f)
    )

    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontFamily = fontFamily,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                append(stringResource(primaryText))
                append(" ")
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = leagueScript,
                    fontSize = 54.sp,
                    brush = gradient
                )
            ) {
                append(stringResource(accentText))
            }
        },
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true)
@Composable
fun TitleTextPreview() {
    DeliciousTheme {
        TitleText(
            primaryText = R.string.try_something,
            accentText = R.string.delicious
        )
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