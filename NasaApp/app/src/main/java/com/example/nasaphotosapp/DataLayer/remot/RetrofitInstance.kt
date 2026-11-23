package com.example.nasaphotosapp.DataLayer.remot

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private const val BASE_URL =
        "https://images-api.nasa.gov/"

    val api : NASAAPIService by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build().create(NASAAPIService::class.java)
    }
}