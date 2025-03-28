package com.example.katturaiproj.u

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katturaiproj.model.Categories
import com.example.katturaiproj.network.HomeApi
import kotlinx.coroutines.launch

class CategoriesScreenViewModel :ViewModel() {
    val categoriesResponse = mutableStateOf(Categories())

    fun getCategories(){
        viewModelScope.launch {
            try {
                categoriesResponse.value = HomeApi.retrofitService.getCategories()

            }catch (e:Exception){
                Log.d("CATEGORIES","Error $e")
            }
        }
    }

    init {
        getCategories()
    }
}