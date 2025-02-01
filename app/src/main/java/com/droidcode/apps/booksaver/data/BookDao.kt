package com.droidcode.apps.booksaver.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAllBooks(): List<Book>

    @Query("SELECT * FROM book WHERE id = (:bookId)")
    fun getSingleBookData(bookId: Int): Book

    @Query("UPDATE book SET title = (:newTitle), author_first_name = (:newAuthorFirstName), " +
            "author_Last_name = (:newAuthorLastName) WHERE id = (:bookId)")
    fun updateBook(
        bookId: Int,
        newTitle: String,
        newAuthorFirstName: String,
        newAuthorLastName: String
    ): Book

    @Query("DELETE FROM book WHERE id = (:bookId)")
    fun deleteBook(bookId: Int): Book
}
