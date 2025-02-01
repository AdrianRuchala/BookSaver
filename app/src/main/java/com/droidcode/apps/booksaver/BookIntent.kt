package com.droidcode.apps.booksaver

import com.droidcode.apps.booksaver.data.Book

sealed class BookIntent {
    data class LoadBooks(val books: BooksState) : BookIntent()
    data class LoadBookData(val book: Book) : BookIntent()
    data class AddBook(val book: Book) : BookIntent()
    data class DeleteBook(val book: Book) : BookIntent()
    data class UpdateBook(
        val bookId: Int,
        val bookTitle: String,
        val authorFirstName: String,
        val authorLastName: String
    ) : BookIntent()
}
