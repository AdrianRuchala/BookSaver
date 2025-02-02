package com.droidcode.apps.booksaver.navigation

interface BookSaverDestinations {
    val route: String
}

object BookListScreen : BookSaverDestinations {
    override val route = "bookListScreen"
}

object AddBookScreen : BookSaverDestinations {
    override val route = "addBookScreen"
}

object BookDetailsScreen : BookSaverDestinations {
    override val route = "bookDetailsScreen/{bookId}"
}
