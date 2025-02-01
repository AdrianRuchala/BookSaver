package com.droidcode.apps.booksaver

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.droidcode.apps.booksaver.data.Book

class BookViewModel() : ViewModel() {
    var bookState = mutableStateOf(BooksState())

    private val bookDao = BookSaverApp.db.bookDao()

    fun onAction(action: BookIntent) {
        when (action) {
            is BookIntent.LoadBooks -> loadBooks()
            is BookIntent.AddBook -> addBook(action.book)
            is BookIntent.UpdateBook -> updateBook()
            is BookIntent.DeleteBook -> deleteBook()

        }
    }

    private fun loadBooks(){
        bookState.value.books = bookDao.getAllBooks()
    }

    private fun addBook(newBook: Book){
        bookDao.insertBook(newBook)
        loadBooks()
    }

    private fun updateBook(){


    }

    private fun deleteBook(){

    }

}
