package com.droidcode.apps.booksaver.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.droidcode.apps.booksaver.BookViewModel
import com.droidcode.apps.booksaver.views.AddBookScreen
import com.droidcode.apps.booksaver.views.BookListScreen


@Composable
fun BookSaverNavHost(modifier: Modifier, navController: NavHostController, viewModel: BookViewModel) {
    NavHost(
        navController = navController,
        startDestination = BookListScreen.route,
        modifier = modifier.padding()
    ) {
        composable(BookListScreen.route) {
            BookListScreen(Modifier, viewModel) { navController.navigateSingleTopTo(AddBookScreen.route) }
        }

        composable(AddBookScreen.route) {
            AddBookScreen(Modifier, viewModel) { navController.navigateUp() }
        }

        composable(BookDetailsScreen.route) {

        }
    }
}

fun NavController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
