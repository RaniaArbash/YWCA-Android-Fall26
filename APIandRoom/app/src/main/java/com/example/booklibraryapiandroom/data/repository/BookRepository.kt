package com.example.booklibraryapiandroom.data.repository

import com.example.booklibraryapiandroom.data.local.BookDao
import com.example.booklibraryapiandroom.data.local.BookEntity
import com.example.booklibraryapiandroom.data.remote.BookApiService
import com.example.booklibraryapiandroom.data.remote.BookDto
import kotlinx.coroutines.flow.Flow

/**
 * Repository class handling data operations from API and Room database.
 */
class BookRepository(
    private val apiService: BookApiService,
    private val bookDao: BookDao
) {
    suspend fun searchBooks(query: String): List<BookDto> {
        val response = apiService.searchBooks(query)
        return response.docs ?: emptyList()
    }

    fun getFavorites(): Flow<List<BookEntity>> {
        return bookDao.getAllFavorites()
    }

    suspend fun addFavorite(book: BookEntity) {
        bookDao.insertFavorite(book)
    }

    suspend fun removeFavorite(book: BookEntity) {
        bookDao.deleteFavorite(book)
    }

    suspend fun isFavorite(bookKey: String): Boolean {
        return bookDao.isFavorite(bookKey) > 0
    }
}
