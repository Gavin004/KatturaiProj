package com.example.katturaiproj

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.katturaiproj.u.FavoritesScreenViewModel
import com.example.katturaiproj.u.SecondScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
       initializer {
           SecondScreenViewModel(
//               katuraiApplForFav().container.favRepository
           )
       }

        initializer {
           FavoritesScreenViewModel(
//               katuraiApplForFav().container.favRepository
           )
        }


    }
}

fun CreationExtras.katuraiApplForFav() :KaturaiApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KaturaiApplication)

