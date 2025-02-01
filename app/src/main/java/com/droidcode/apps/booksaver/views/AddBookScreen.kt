package com.droidcode.apps.booksaver.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droidcode.apps.booksaver.BookIntent
import com.droidcode.apps.booksaver.BookViewModel
import com.droidcode.apps.booksaver.R
import com.droidcode.apps.booksaver.data.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddBookScreen(modifier: Modifier, viewModel: BookViewModel, navigateUp: () -> Unit) {
    var bookTitle by remember { mutableStateOf("") }
    var authorFirstName by remember { mutableStateOf("") }
    var authorLastName by remember { mutableStateOf("") }

    Scaffold(topBar = { TopBar(modifier) { navigateUp() } }) { padding ->
        modifier.padding(padding)

        Column(
            modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = {
                    Text(
                        stringResource(R.string.title_label)
                    )
                },
                isError = bookTitle.isEmpty()
            )

            TextField(
                value = authorFirstName,
                onValueChange = { authorFirstName = it },
                modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = {
                    Text(
                        stringResource(R.string.author_first_name_label)
                    )
                },
                isError = authorFirstName.isEmpty()
            )

            TextField(
                value = authorLastName,
                onValueChange = { authorLastName = it },
                modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = {
                    Text(
                        stringResource(R.string.author_last_name_label)
                    )
                },
                isError = authorLastName.isEmpty()
            )

            Button(onClick = {
                val newBook = Book(0, bookTitle, authorFirstName, authorLastName)
                if (bookTitle.isNotEmpty() && authorFirstName.isNotEmpty() && authorLastName.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.onAction(BookIntent.AddBook(newBook))
                    }
                }
            }) {
                Text(stringResource(R.string.save))
            }
        }
    }
}

@Composable
private fun TopBar(modifier: Modifier, navigateUp: () -> Unit) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(120.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clickable { navigateUp() }
            )

            Text(
                stringResource(id = R.string.add_book),
                modifier = Modifier
                    .padding(all = 8.dp),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
    }
}
