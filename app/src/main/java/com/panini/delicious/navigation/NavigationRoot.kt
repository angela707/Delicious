package com.panini.delicious.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.panini.core.presentation.navigation.Routes
import com.panini.recipe_detail.presentation.RecipeDetailScreen
import com.panini.recipe_list.presentation.RecipeListScreen

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Recipe
    ) {
        recipeGraph(navController)
    }
}

private fun NavGraphBuilder.recipeGraph(navController: NavHostController) {
    navigation<Routes.Recipe>(
        startDestination = Routes.RecipeList,
    ) {
        composable<Routes.RecipeList> {
            RecipeListScreen(
                onClick = { recipeId ->
                    navController.navigate(Routes.RecipeDetails(recipeId = recipeId))
                }
            )
        }
        composable<Routes.RecipeDetails> {
            RecipeDetailScreen(
                onNavigateBack = navController::popBackStack
            )
        }
    }
}
