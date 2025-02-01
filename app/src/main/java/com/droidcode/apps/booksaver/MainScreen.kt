package com.droidcode.apps.booksaver

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.droidcode.apps.booksaver.navigation.BookSaverNavHost

@Composable
fun MainScreen(modifier: Modifier, navController: NavHostController, viewModel: BookViewModel){
    Scaffold() { padding ->
        BookSaverNavHost(modifier.padding(padding), navController, viewModel)
    }
}
