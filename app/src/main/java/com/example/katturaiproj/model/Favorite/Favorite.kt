package com.example.katturaiproj.model.Favorite

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id:Int = 0 ,
    val imgUrl:String,
    val title:String,
    val desc:String,
)
