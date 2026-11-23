package com.example.nasaphotosapp.DataLayer


data class NASAResponse(
    val collection: NASACollection? = null
)

data class NASACollection(
    val version: String? = null,
    val href : String? = null,
    val items: List<NASAItem>? = null
)

data class NASAItem(
    val href : String? = null,
    val data : List<NASAItemData>? = null,
    val links : List<NASAItemLink>? = null
)

data class NASAItemData(
    val title: String? = null,
    val nasa_id: String? = null,
    val description : String? = null,
    val date_created: String? = null,
    val keywords: List<String>? = null,
    val media_type : String? = null
)

data class NASAItemLink(
    val href: String? = null,
    val rel : String? = null,
    val render: String? = null
)






