package com.droidcode.apps.booksaver.navigation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.droidcode.apps.booksaver.BookIntent
import com.droidcode.apps.booksaver.BookViewModel
import com.droidcode.apps.booksaver.BooksState
import com.droidcode.apps.booksaver.views.AddBookScreen
import com.droidcode.apps.booksaver.views.BookDetailsScreen
import com.droidcode.apps.booksaver.views.BookListScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BookSaverNavHost(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: BookViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BookListScreen.route,
        modifier = modifier.padding()
    ) {
        composable(BookListScreen.route) {
            BookListScreen(
                Modifier,
                viewModel,
                { navController.navigateSingleTopTo(AddBookScreen.route) },
                { navController.navigateSingleTopTo((BookDetailsScreen.route)) }
            )
        }

        composable(AddBookScreen.route) {
            AddBookScreen(Modifier, viewModel) { navController.navigateUp() }
        }

        composable(BookDetailsScreen.route) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.onAction(BookIntent.LoadBooks(BooksState()))
            }
            val bookData = viewModel.bookState.value.books.find { it.id == bookId }
            bookData?.let {
                BookDetailsScreen(
                    Modifier,
                    viewModel,
                    bookId
                ) { navController.navigateUp() }
            }
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
