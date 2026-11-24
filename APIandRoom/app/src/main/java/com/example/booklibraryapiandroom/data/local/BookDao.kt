package com.example.booklibraryapiandroom.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for interacting with the books table in Room.
 */
@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(book: BookEntity)

    @Delete
    suspend fun deleteFavorite(book: BookEntity)

    @Query("SELECT * FROM books")
    fun getAllFavorites(): Flow<List<BookEntity>>

    @Query("SELECT COUNT(*) FROM books WHERE `key` = :bookKey")
    suspend fun isFavorite(bookKey: String): Int
}
