package com.example.katturaiproj.u

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katturaiproj.model.Favorite.Favorite
import com.example.katturaiproj.model.Favorite.FavoriteRepository
import com.example.katturaiproj.model.Favorite.Graph
import com.example.katturaiproj.model.SingleArticle
import com.example.katturaiproj.network.HomeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class SecondScreenViewModel(
    private val favoriteRepository: FavoriteRepository = Graph.favoriteRepository
) : ViewModel() {

//    val itemId:Int = savedStateHandle["id"] ?: 0
//    var favorite = mutableStateOf(false)



     val updateStatus = mutableStateOf(SingleArticle())
//    val updateStatus: LiveData<SingleArticle> = _updateStatus

    fun toggleFav(id: Int?,favorite: Favorite){ //no suspend becoz in rememberCoroutineScope in ui
        if(id == null){
            Log.d("FAV-SS","null ")
            viewModelScope.launch(Dispatchers.IO){
                favoriteRepository.insertFav(favorite)
            }
        }else{
            Log.d("Fav-ss2","else")
            viewModelScope.launch(Dispatchers.IO) {
                favoriteRepository.deleteFav(favorite)
            }
        }
    }

    fun check(id: Int): Flow<Favorite> {
         return favoriteRepository.getFav(id)
    }


     fun getSingleArticleVM(id:Int){

        viewModelScope.launch {
            try {
                val rawJson = """ { "id": $id } """.trimIndent() // Send integer as JSON
                val requestBody = rawJson.toRequestBody("application/json".toMediaTypeOrNull())
                updateStatus.value = HomeApi.retrofitService.getSingleArticle(requestBody)
                Log.d("HOME",updateStatus.value.toString())
            }catch (e:Exception){
//                todo
                Log.d("HOMEERR","ERRor $e")
            }

        }
    }

    fun authorNameFun():String{
        updateStatus.value.article?.content?.forEach {
            if(it.authorName != ""){
                return it.authorName ?: ""
            }
        }
        return ""
    }
//


}