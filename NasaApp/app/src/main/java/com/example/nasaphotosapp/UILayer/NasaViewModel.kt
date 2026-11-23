package com.example.nasaphotosapp.UILayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaphotosapp.DataLayer.MarsImage
import com.example.nasaphotosapp.DataLayer.NASARepository
import kotlinx.coroutines.launch

class NasaViewModel(private val NasaRepo: NASARepository = NASARepository()) :
    ViewModel(){

    var searchQuery by mutableStateOf("Mars")
        private set

    var isLoading by mutableStateOf(true)
        private set

    var images by mutableStateOf<List<MarsImage>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
       searchImages("Mars")
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun searchImages(query: String) {
        searchQuery = query
        isLoading = true
        viewModelScope.launch {
         val result =   NasaRepo.searchForMarsImages(query)// this is suspend function and can't run in main thread
            isLoading = false
            result.fold(
                onSuccess = {fetchedImage -> images = fetchedImage },
                onFailure = { exception -> errorMessage = "There is an error: ${exception.message}"})

        }
    }

    fun getImageById(nasaId: String): MarsImage? {
        return images.firstOrNull { it.nasaid == nasaId }
    }


}