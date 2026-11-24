package com.example.booklibraryapiandroom.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API service interface for Open Library endpoints.
 */
interface BookApiService {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): BookResponse
}
