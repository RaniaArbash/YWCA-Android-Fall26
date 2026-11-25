package com.example.assignment2template.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Reusable Retrofit configuration instance.
 *
 * TODO: Students must update [BASE_URL] to point to their chosen API base URL:
 * - DummyJSON Products API: https://dummyjson.com/
 * - Rick and Morty API: https://rickandmortyapi.com/api/
 * - PokéAPI: https://pokeapi.co/api/v2/
 *
 * Students must also create their own API interface and response data models in this package
 * or subpackages, and make network requests using this instance.
 */
object RetrofitInstance {

    private const val BASE_URL = "https://dummyjson.com/" // Replace with your chosen API base URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
