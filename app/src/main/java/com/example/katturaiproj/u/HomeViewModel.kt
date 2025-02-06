package com.example.katturaiproj.u

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.katturaiproj.model.Article
import com.example.katturaiproj.model.ArticleRequest
import com.example.katturaiproj.model.ArticleResponse
import com.example.katturaiproj.model.HomeItems
import com.example.katturaiproj.network.HomeApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface UiState {
    data class Success(val homeItems: List<HomeItems>) : UiState
    object Error : UiState
    object Loading : UiState
}

class HomeViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set


    init {
        getHomeItems()
    }


    fun getHomeItems() {
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {
                val listResult = HomeApi.retrofitService.getItems()
                UiState.Success(
                    listResult.home
                )
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }
        }
    }

//    var responseMessage: Article? = null
//    var res:String = ""
//
//    fun postArticle(articleId: Int) {
//        viewModelScope.launch {
//            try {
//                val request = ArticleRequest(id = articleId)
//                val response = HomeApi.retrofitService.postArticle(request)
//
////                if (response.isSuccessful) {
////                    responseMessage = response.body()?.message ?: "Success"
////                    println("Response: ${response.body()}")
////                } else {
////                    responseMessage = "Error: ${response.code()}"
////                }
//                responseMessage = response.article
//            } catch (e: IOException) {
//                responseMessage = null
//                res = e.toString()
//            } catch (e: HttpException) {
//                responseMessage = null
//                res = e.toString()
//            }
//        }
//    }



}