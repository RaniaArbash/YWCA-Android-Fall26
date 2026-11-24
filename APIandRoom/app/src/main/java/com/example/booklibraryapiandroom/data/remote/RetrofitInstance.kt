package com.example.booklibraryapiandroom.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object providing the Retrofit instance and API service.
 */
object RetrofitInstance {
    private const val BASE_URL = "https://openlibrary.org/"

    val api: BookApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApiService::class.java)
    }
}
