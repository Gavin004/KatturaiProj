package com.example.katturaiproj.u

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katturaiproj.model.Authors
import com.example.katturaiproj.network.HomeApi
import kotlinx.coroutines.launch

class AuthorsScreenViewModel : ViewModel() {
    val authorsResponse = mutableStateOf(Authors())

    fun getAuthors(){
        viewModelScope.launch {
            try {
                authorsResponse.value = HomeApi.retrofitService.getAuthors()
            }catch (e:Exception){
                Log.d("AuthorErr","Error -> $e")
            }
        }
    }

    init {
        getAuthors()
    }
}