package com.example.katturaiproj.model

import kotlinx.serialization.Serializable


@Serializable
data class Tags(
    val message:String = "",
    val tags : List<Tag> = listOf()
)

@Serializable
data class Tag(
    val id:Int = 0,
    val name:String = "",
    val desc:String = "",
    val createdAt: String = "",
    val updatedAt:String = ""
)
