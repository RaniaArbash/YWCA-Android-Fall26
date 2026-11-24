package com.example.booklibraryapiandroom.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity representing a saved favorite book in the local database.
 */
@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val key: String,
    val title: String,
    val author: String,
    val firstPublishYear: Int?,
    val coverId: Int?
)
