package com.panini.recipe_list.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.panini.recipe_list.R

@Composable
fun RecipeCard(
    modifier: Modifier = Modifier,
    name: String,
    prepTimeMinutes: Int?,
    thumbnailUrl: String?,
    yields: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = thumbnailUrl,
                contentDescription = null,
                error = painterResource(com.panini.core.R.drawable.error_image),
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            ),
                            startY = 300f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart),
            ) {
                Text(
                    text = name.uppercase(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineMedium
                )

                if (prepTimeMinutes != null && prepTimeMinutes != 0) {
                    Text(
                        text = stringResource(com.panini.core.R.string.prep_time, prepTimeMinutes),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                yields?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun RecipeCardPreview() {
    RecipeCard(
        name = "Pancakes",
        prepTimeMinutes = 10,
        thumbnailUrl = "thumb",
        yields = "Serves 2"
    ) { }
}