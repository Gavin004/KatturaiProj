package com.example.katturaiproj.u

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katturaiproj.model.Favorite.Favorite
import com.example.katturaiproj.model.Favorite.FavoriteRepository
import com.example.katturaiproj.model.Favorite.Graph
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoritesScreenViewModel(
    val favoriteRepository: FavoriteRepository = Graph.favoriteRepository
) :ViewModel() {
    val favoriteListState:StateFlow<List<Favorite>> =
        favoriteRepository.getAllFav().map {
            it
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = listOf()
        )
}