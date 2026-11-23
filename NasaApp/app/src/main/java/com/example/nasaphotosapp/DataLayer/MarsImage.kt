package com.example.nasaphotosapp.DataLayer



data class MarsImage(
    val nasaid: String,
    val title: String,
    val description: String,
    val datacreated : String,
    val keywords: List<String>,
    val imageUrl: String
)


object MarsImageMapper {
    fun mapToMarsImage(item: NASAItem): MarsImage? {
       val itemData =  item.data?.firstOrNull() ?: return null
        val id = itemData.nasa_id ?: return null
        val titleText = itemData.title?.takeIf { it.isNotBlank() } ?: "NASA Mars Image"
        val desc = itemData.description?.takeIf { it.isNotBlank() } ?: ""
        val date = itemData.date_created?.take(10) ?: "" // Formats ISO timestamp to YYYY-MM-DD
        val keys = itemData.keywords?.filter { it.isNotBlank() } ?: emptyList()

        val rawUrl = item.links?.firstOrNull { it.render == "image" || it.rel == "preview" }?.href
            ?: item.links?.firstOrNull()?.href
            ?: return null

        // Ensure URL uses HTTPS to comply with Android network security guidelines
        val secureUrl = if (rawUrl.startsWith("http://")) {
            rawUrl.replaceFirst("http://", "https://")
        } else {
            rawUrl
        }

        return MarsImage(
            nasaid = id,
            title = titleText,
            description = desc,
            datacreated = date,
            keywords = keys,
            imageUrl = secureUrl
        )
    }
}