package com.example.booklibraryapiandroom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.booklibraryapiandroom.data.local.BookEntity
import com.example.booklibraryapiandroom.data.remote.BookDto
import com.example.booklibraryapiandroom.ui.viewmodel.BookViewModel

@Composable
fun SearchScreen(viewModel: BookViewModel) {
    val query by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { newText -> viewModel.updateSearchQuery(newText) },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Search book title or author...") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                        viewModel.searchBooks(query)
                    }
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    keyboardController?.hide()
                    viewModel.searchBooks(query)
                }
            ) {
                Icon(Icons.Default.Search, contentDescription = "Search")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(searchResults) { book ->
                            BookItemRow(
                                book = book,
                                favorites = favorites,
                                onFavoriteClick = { isFav ->
                                    if (isFav) {
                                        viewModel.removeFavoriteByDto(book)
                                    } else {
                                        viewModel.addFavorite(book)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookItemRow(
    book: BookDto,
    favorites: List<BookEntity>,
    onFavoriteClick: (Boolean) -> Unit
) {
    val isFavorite = favorites.any { it.key == book.key }
    val coverUrl = book.coverId?.let { "https://covers.openlibrary.org/b/id/$it-M.jpg" }
    val author = book.authorName?.firstOrNull() ?: "Unknown Author"
    val year = book.firstPublishYear?.toString() ?: "N/A"

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = coverUrl ?: "",
                contentDescription = book.title,
                modifier = Modifier
                    .width(60.dp)
                    .height(90.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = book.title ?: "Unknown Title",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Author: $author",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "First Published: $year",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            IconButton(onClick = { onFavoriteClick(isFavorite) }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}
