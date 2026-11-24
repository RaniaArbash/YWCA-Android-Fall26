package com.example.booklibraryapiandroom.data.remote

/**
 * Data class representing the search response from the Open Library API.
 */
data class BookResponse(
    val docs: List<BookDto>?
)

/**
 * Data class representing an individual book DTO from the API response without @SerializedName.
 */
data class BookDto(
    val key: String?,
    val title: String?,
    val author_name: List<String>?,
    val first_publish_year: Int?,
    val cover_i: Int?
) {
    val authorName: List<String>? get() = author_name
    val firstPublishYear: Int? get() = first_publish_year
    val coverId: Int? get() = cover_i
}
