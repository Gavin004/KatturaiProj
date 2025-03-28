package com.example.katturaiproj.model.Favorite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Favorite::class],
    version = 2,
    exportSchema = false
)
abstract class FavoriteDatabase:RoomDatabase() {
    abstract fun favoriteDao():FavoriteDAO

//    companion object{
//        @Volatile
//        private var Instance : FavoriteDatabase? = null
//
//        @OptIn(InternalCoroutinesApi::class)
//        fun getDatabase(context: Context) : FavoriteDatabase{
//            return Instance ?: synchronized(this){
//                Room.databaseBuilder(context,FavoriteDatabase :: class.java,"favorite_database")
//                    .fallbackToDestructiveMigration()
//                    .build()
//            }
//        }
//    }
}