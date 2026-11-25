package com.example.booklibraryapiandroom.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibraryapiandroom.data.local.BookEntity
import com.example.booklibraryapiandroom.data.remote.BookDto
import com.example.booklibraryapiandroom.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel managing search query, search results, loading state, error messages, and favorites.
 * Injected with BookRepository via ViewModel Factory.
 */
class BookViewModel(private val repository: BookRepository ) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<BookDto>>(emptyList())
    val searchResults: StateFlow<List<BookDto>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    val favorites: StateFlow<List<BookEntity>> = repository.getFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchBooks(query: String) {
        if (query.isBlank()) return
        _searchQuery.value = query
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val results = repository.searchBooks(query)
                _searchResults.value = results
                if (results.isEmpty()) {
                    _errorMessage.value = "No books found."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load books. Check your internet connection."
                _searchResults.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addFavorite(dto: BookDto) {
        viewModelScope.launch {
            val key = dto.key ?: return@launch
            val title = dto.title ?: "Unknown Title"
            val author = dto.author_name?.firstOrNull() ?: "Unknown Author"
            val entity = BookEntity(
                key = key,
                title = title,
                author = author,
                firstPublishYear = dto.first_publish_year,
                coverId = dto.cover_i
            )
            repository.addFavorite(entity)
        }
    }

    fun removeFavorite(entity: BookEntity) {
        viewModelScope.launch {
            repository.removeFavorite(entity)
        }
    }

    fun removeFavoriteByDto(dto: BookDto) {
        viewModelScope.launch {
            val key = dto.key ?: return@launch
            val title = dto.title ?: "Unknown Title"
            val author = dto.author_name?.firstOrNull() ?: "Unknown Author"
            val entity = BookEntity(
                key = key,
                title = title,
                author = author,
                firstPublishYear = dto.first_publish_year,
                coverId = dto.cover_i
            )
            repository.removeFavorite(entity)
        }
    }
}
