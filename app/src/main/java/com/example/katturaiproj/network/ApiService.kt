package com.example.katturaiproj.network

import com.example.katturaiproj.model.ApiResponse
import com.example.katturaiproj.model.ArticleRequest
import com.example.katturaiproj.model.ArticleResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


private const val BASE_URL =
    "https://api.kadalpura.com"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


interface ApiService {
    @GET("articles/home")
    suspend fun getItems(): ApiResponse


    @Headers("Content-Type: application/json")
    @POST("article/home") // Replace with actual API endpoint
    suspend fun postArticle(@Body request: ArticleRequest): ArticleResponse
}


object HomeApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}