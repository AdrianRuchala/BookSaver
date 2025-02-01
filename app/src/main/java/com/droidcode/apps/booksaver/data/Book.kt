package com.droidcode.apps.booksaver.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "author_first_name") val authorFirstName: String?,
    @ColumnInfo(name = "author_last_name") val authorLastName: String?
)
