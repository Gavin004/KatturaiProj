package com.example.katturaiproj.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeItems(
    val type: Int?,
    val title: String? = "",
    @SerialName("short_desc") val shortDesc: String? =  "",
    @SerialName("image_url") val imageUrl: String? = "",
    val id: Int? = 0,
    @SerialName("id_1") val id1: Int? = null,  // Optional field
    @SerialName("id_2") val id2: Int? = null,   // Optional field
    @SerialName("id_3") val id3: Int? = null,  // Optional field
    @SerialName("id_4") val id4: Int? = null   // Optional field
)

@Serializable
data class ApiResponse(
    val home: List<HomeItems>  // The "home" key contains a list of HomeItem objects
)

@Serializable
data class ArticleResponse(
    val message : String?,
    val article : Article?,
)

@Serializable
data class Article(
    @SerialName("id") val id: Int,
    @SerialName("category_ids") val categoryIds: List<Int>,
    @SerialName("tag_ids") val tagIds: List<Int>,
    @SerialName("author_id") val authorId: Int,
    @SerialName("title") val title: String,
    @SerialName("short_desc") val shortDesc: String,
    @SerialName("keywords") val keywords: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    @SerialName("banner_url") val bannerUrl: String,
    @SerialName("score") val score: Int,
    @SerialName("likes") val likes: Int,
    @SerialName("dislikes") val dislikes: Int,
    @SerialName("viewed") val viewed: Int,
    @SerialName("content") val content: List<ContentItem>,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("updatedAt") val updatedAt: String
)

@Serializable
data class ContentItem(
    @SerialName("title") val title: String?,
    @SerialName("quote") val quote: String?,
    @SerialName("para") val para: String?,
    @SerialName("img_url") val imgUrl: String?,
    @SerialName("img_desc") val imgDesc: String?,
    @SerialName("relevance") val relevance: List<RelatedArticle>?
)

@Serializable
data class RelatedArticle(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    @SerialName("date_of_publish") val dateOfPublish: String
)

@Serializable
data class ArticleRequest(
    val id: Int
)