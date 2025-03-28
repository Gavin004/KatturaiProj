package com.example.katturaiproj.u

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katturaiproj.model.Tags
import com.example.katturaiproj.network.HomeApi
import kotlinx.coroutines.launch

class TagsScreenViewModel : ViewModel() {
    val tags = mutableStateOf(Tags())

    fun getTags(){
        viewModelScope.launch {
            try {
                tags.value = HomeApi.retrofitService.getTags()
            }catch (e:Exception){
                Log.d("TAGErr","Error -> $e")
            }
        }
    }

    init {
        getTags()
    }
}