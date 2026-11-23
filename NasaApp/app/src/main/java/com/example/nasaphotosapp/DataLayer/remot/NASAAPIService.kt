package com.example.nasaphotosapp.DataLayer.remot

import com.example.nasaphotosapp.DataLayer.NASAResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NASAAPIService {
    @GET("search")
    suspend fun searchImages( /// sync == running in a different thread ==
        // keep the UI active (UI runs in the main thread )
        // this in Kotlin means this function should run in a coroutine
        @Query("q") query :String,
        @Query("media_type") mediaType : String = "image",
        @Query("page") page : Int = 1,
        @Query("page_size") pageSize : Int = 30
    ): NASAResponse
}

