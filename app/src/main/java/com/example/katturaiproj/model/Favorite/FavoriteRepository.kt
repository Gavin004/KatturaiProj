package com.example.katturaiproj.model.Favorite

import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val favoriteDAO: FavoriteDAO) {

    suspend fun insertFav(favorite: Favorite){
        favoriteDAO.insert(favorite)
    }

    suspend fun deleteFav(favorite: Favorite){
        favoriteDAO.delete(favorite)
    }

    fun getFav(id:Int) : Flow<Favorite>{
        return favoriteDAO.getFavorite(id)
    }

    fun getAllFav() : Flow<List<Favorite>>{
        return favoriteDAO.getAllFavorites()
    }


}