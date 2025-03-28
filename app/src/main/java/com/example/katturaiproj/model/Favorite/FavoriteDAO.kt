package com.example.katturaiproj.model.Favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("select * from favorites")
    fun getAllFavorites():Flow<List<Favorite>>

    @Query("select * from favorites where id = :id")
    fun getFavorite(id:Int):Flow<Favorite>

}