package com.example.katturaiproj.network

import com.example.katturaiproj.model.ApiResponse
import com.example.katturaiproj.model.Authors
import com.example.katturaiproj.model.Categories
import com.example.katturaiproj.model.SingleArticle
import com.example.katturaiproj.model.Tags

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
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
    @GET("articles/home") // list of articles for home for homescreen/katuraiApp composable
    suspend fun getItems(): ApiResponse

    @POST("articles/get") //getting a particular article with id  for secondscreen
    suspend fun getSingleArticle(@Body requestBody: RequestBody):SingleArticle

    @GET("articles/tags") // list of tags for tagScreen
    suspend fun getTags() : Tags

    @GET("articles/authors") // list of authors for authorScreen
    suspend fun getAuthors() : Authors

    @GET("articles/categories") //list of categories for categoriesScreen
    suspend fun getCategories() : Categories

}


object HomeApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}