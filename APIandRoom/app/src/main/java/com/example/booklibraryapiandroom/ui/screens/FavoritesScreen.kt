package com.example.booklibraryapiandroom.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.booklibraryapiandroom.data.local.BookEntity
import com.example.booklibraryapiandroom.ui.viewmodel.BookViewModel

@Composable
fun FavoritesScreen(viewModel: BookViewModel) {
    val favorites by viewModel.favorites.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Favorite Books",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No favorite books saved yet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favorites) { book ->
                    FavoriteItemRow(
                        book = book,
                        onRemoveClick = { viewModel.removeFavorite(book) }
                    )
                }
            }
        }
    }
}

@Composable
fun FavoriteItemRow(
    book: BookEntity,
    onRemoveClick: () -> Unit
) {
    val coverUrl = book.coverId?.let { "https://covers.openlibrary.org/b/id/$it-M.jpg" }
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
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Author: ${book.author}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "First Published: $year",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            IconButton(onClick = onRemoveClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove Favorite",
                    tint = Color.Red
                )
            }
        }
    }
}
