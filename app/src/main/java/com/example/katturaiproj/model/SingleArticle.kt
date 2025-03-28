package com.example.katturaiproj.model


//import com.squareup.moshi.Json
//import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleArticle(
    @SerialName( "message")
    val message: String? = "",
    @SerialName( "article")
    val article: Article? = null
) {
    @Serializable
    data class Article(
        @SerialName( "id")
        val id: Int? = null,
        @SerialName( "category_ids")
        val categoryIds: List<Int>? = null,
        @SerialName( "tag_ids")
        val tagIds: List<Int>? = null,
        @SerialName( "author_id")
        val authorId: Int? = null,
        @SerialName( "title")
        val title: String? = "",
        @SerialName( "short_desc")
        val shortDesc: String? = "",
        @SerialName( "keywords")
        val keywords: String? = "",
        @SerialName( "thumbnail_url")
        val thumbnailUrl: String? = "",
        @SerialName( "banner_url")
        val bannerUrl: String? = "",
        @SerialName( "score")
        val score: Int? = null,
        @SerialName( "likes")
        val likes: Int? = null,
        @SerialName( "dislikes")
        val dislikes: Int? = null,
        @SerialName( "viewed")
        val viewed: Int? = null,
        @SerialName( "content")
        val content: List<Content>? = null,
        @SerialName( "createdAt")
        val createdAt: String? = "",
        @SerialName( "updatedAt")
        val updatedAt: String? = ""
    ) {
        @Serializable
        data class Content(
            @SerialName( "title")
            val title: String? = "",
            @SerialName( "quote")
            val quote: String? = "",
            @SerialName( "para")
            val para: String? = "",
            @SerialName( "img_url")
            val imgUrl: String? = "",
            @SerialName( "img_desc")
            val imgDesc: String? = "",
            @SerialName( "relevance")
            val relevance: List<Relevance>? = null,
            @SerialName( "author_name")
            val authorName: String? = "",
            @SerialName( "tag_names")
            val tagNames: List<String>? = null,
            @SerialName("sub_title")
            val subTitle:String? = ""
        ) {
            @Serializable
            data class Relevance(
                @SerialName( "id")
                val id: Int? = null,
                @SerialName( "title")
                val title: String? = "",
                @SerialName( "thumbnail_url")
                val thumbnailUrl: String? = "",
                @SerialName( "date_of_publish")
                val dateOfPublish: String? = ""
            )
        }
    }
}