package com.droidcode.apps.booksaver.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droidcode.apps.booksaver.BookIntent
import com.droidcode.apps.booksaver.BookViewModel
import com.droidcode.apps.booksaver.BooksState
import com.droidcode.apps.booksaver.R
import com.droidcode.apps.booksaver.data.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BookListScreen(modifier: Modifier, viewModel: BookViewModel, navigateToBookAddScreen: () -> Unit) {

    CoroutineScope(Dispatchers.IO).launch {
        viewModel.onAction(BookIntent.LoadBooks(BooksState()))
    }

    Scaffold(
        floatingActionButton = { AddBookFloatingButton { navigateToBookAddScreen() } },
        topBar = { TopBar(modifier) }
    ) { padding ->

        LazyColumn(modifier.fillMaxSize().padding(padding)) {
            items(viewModel.bookState.value.books) { book ->
                BookPlate(modifier, book)
            }
        }
    }
}

@Composable
private fun TopBar(modifier: Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(id = R.string.saved_books),
                modifier = Modifier
                    .padding(all = 8.dp),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
    }
}

@Composable
fun AddBookFloatingButton(navigateToBookAddScreen: () -> Unit) {
    FloatingActionButton(onClick = { navigateToBookAddScreen() }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Composable
fun BookPlate(modifier: Modifier, bookState: Book){
    Column(
        modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(4.dp, MaterialTheme.colorScheme.primary ,shape = MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.small),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier.padding(16.dp)
        ) {
            bookState.title?.let { Text(it, style = MaterialTheme.typography.titleLarge) }

            Spacer(modifier = modifier.padding(4.dp))

            Row {
                Text(
                    stringResource(R.string.author),
                    modifier.padding(end = 4.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "${bookState.authorLastName} ${bookState.authorLastName} ")
            }
        }
    }
}
