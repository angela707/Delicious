package com.panini.recipe.presentation.preview_providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.panini.recipe.domain.Recipe

class RecipesPreviewProvider : PreviewParameterProvider<List<Recipe>> {
    override val values: Sequence<List<Recipe>>
        get() = sequenceOf(
            listOf(
                Recipe(
                    id = 1,
                    name = "Classic Pancakes",
                    description = "Fluffy pancakes perfect for breakfast",
                    prepTimeMinutes = 15,
                    thumbnailUrl = "https://example.com/pancakes.jpg",
                    videoUrl = "https://example.com/pancakes.mp4",
                    yields = "Serves 4"
                ),
                Recipe(
                    id = 2,
                    name = "Spaghetti Carbonara",
                    description = "Creamy pasta with bacon and cheese",
                    prepTimeMinutes = 20,
                    thumbnailUrl = "https://example.com/carbonara.jpg",
                    videoUrl = "https://example.com/carbonara.mp4",
                    yields = "Serves 2"
                ),
                Recipe(
                    id = 3,
                    name = "Chicken Teriyaki",
                    description = "Juicy chicken glazed with teriyaki sauce",
                    prepTimeMinutes = 25,
                    thumbnailUrl = "https://example.com/teriyaki.jpg",
                    videoUrl = "https://example.com/teriyaki.mp4",
                    yields = "Serves 3"
                ),
                Recipe(
                    id = 4,
                    name = "Chocolate Brownies",
                    description = "Rich and fudgy chocolate brownies",
                    prepTimeMinutes = 30,
                    thumbnailUrl = "https://example.com/brownies.jpg",
                    videoUrl = "https://example.com/brownies.mp4",
                    yields = "Serves 8"
                )
            )
        )
}