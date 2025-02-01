package com.droidcode.apps.booksaver

import android.app.Application
import androidx.room.Room
import com.droidcode.apps.booksaver.data.BookDatabase

class BookSaverApp: Application() {
    companion object {
        lateinit var db: BookDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            BookDatabase::class.java, "books-db"
        ).build()
    }
}
