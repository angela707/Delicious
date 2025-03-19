package com.panini.recipe_detail.presentation

import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.panini.core.domain.Recipe
import com.panini.core.presentation.theme.DeliciousTheme
import com.panini.recipe_detail.R
import com.panini.recipe_detail.presentation.components.TextWithIcon
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailsViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        RecipeDetailsScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding() + 24.dp),
            state = state.value,
            onBackClick = onNavigateBack
        )
    }
}

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    state: RecipeDetailsUiState,
    onBackClick: () -> Unit
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

        state.recipe != null -> {
            RecipeDetailsInternal(
                modifier = modifier,
                recipe = state.recipe,
                onBackClick = onBackClick
            )
        }
    }
}


@Composable
fun RecipeDetailsInternal(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (!recipe.videoUrl.isNullOrEmpty()) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    factory = { context ->
                        VideoView(context).apply {
                            setVideoPath(recipe.videoUrl)
                            setOnPreparedListener { it.isLooping = true }
                            start()
                        }
                    }
                )
            } else {
                AsyncImage(
                    model = recipe.thumbnailUrl,
                    contentDescription = null,
                    error = painterResource(com.panini.core.R.drawable.error_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }

            IconButton(
                onClick = { onBackClick() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(35.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                        shape = CircleShape
                    )
                    .align(
                        Alignment.TopStart
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = recipe.name,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (recipe.prepTimeMinutes != null && recipe.prepTimeMinutes != 0) {
                    TextWithIcon(
                        title = stringResource(
                            com.panini.core.R.string.prep_time,
                            recipe.prepTimeMinutes!!
                        ),
                        icon = R.drawable.ic_timer
                    )
                }

                recipe.yields?.let {
                    TextWithIcon(
                        title = it,
                        icon = R.drawable.ic_meal,
                        iconColor = MaterialTheme.colorScheme.primary
                    )
                }
            }

            recipe.description?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsPreview() {
    DeliciousTheme {
        RecipeDetailsInternal(
            recipe = Recipe(
                id = 1,
                name = "Chocolate Cake",
                description = "Rich and moist chocolate cake perfect for dessert.",
                prepTimeMinutes = 45,
                thumbnailUrl = "https://via.placeholder.com/400",
                videoUrl = null,
                yields = "Serves 8"
            ),
            onBackClick = {}
        )
    }
}