package com.example.katturaiproj.model

import kotlinx.serialization.Serializable

@Serializable
data class Authors(
    val message:String = "",
    val authors:List<Author> = listOf()
)

@Serializable
data class Author(
    val id:Int = 0,
    val name:String = "",
    val title:String = "",
    val bio:String = "",
    val createdAt:String = "",
    val updatedAt:String = ""
)

