package com.example.katturaiproj.model

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val message :String = "",
    val categories : List<Category> = listOf()
)

@Serializable
data class Category(
    val id:Int = 0,
    val name:String = "",
    val desc:String = "",
    val createdAt:String = "",
    val updatedAt:String = ""
)