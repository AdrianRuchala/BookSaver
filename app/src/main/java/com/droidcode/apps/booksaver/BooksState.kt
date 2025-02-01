package com.droidcode.apps.booksaver

import com.droidcode.apps.booksaver.data.Book

data class BooksState(
    var books: List<Book> = emptyList()
)
