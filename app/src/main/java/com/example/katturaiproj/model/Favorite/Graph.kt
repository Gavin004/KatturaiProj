package com.example.katturaiproj.model.Favorite

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: FavoriteDatabase

    val favoriteRepository by lazy {
        FavoriteRepository(favoriteDAO = database.favoriteDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,FavoriteDatabase::class.java,"fav.db")
            .fallbackToDestructiveMigration().build()
    }
}