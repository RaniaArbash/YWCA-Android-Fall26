package com.example.nasaphotosapp.DataLayer

import com.example.nasaphotosapp.DataLayer.remot.NASAAPIService
import com.example.nasaphotosapp.DataLayer.remot.RetrofitInstance

// bring the images from the data source
// send them to the viewmodel
// viewmodel has no idea from where those images come.
class NASARepository(private val apiService : NASAAPIService = RetrofitInstance.api) {
suspend fun searchForMarsImages ( query: String): Result<List<MarsImage>> {
   return try {
        val response = apiService.searchImages(query)
        val items = response.collection?.items ?: emptyList()
        val marsImages = items.mapNotNull { item -> MarsImageMapper.mapToMarsImage(item) }
        Result.success(marsImages)
    }catch (e: Exception){
        Result.failure(e)
    }
}
}
