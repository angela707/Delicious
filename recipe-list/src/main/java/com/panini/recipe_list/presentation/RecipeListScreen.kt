package com.panini.recipe_list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = koinViewModel(),
    onButtonClicked: (String) -> Unit = {},
) {
    val state = viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.value.recipes) {
                Text(
                    modifier = Modifier
                        .clickable {
                            onButtonClicked(it.name)
                        },
                    text = it.name
                )
            }
        }
    }
}