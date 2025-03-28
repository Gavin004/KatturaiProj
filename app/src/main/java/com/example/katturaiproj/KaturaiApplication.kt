package com.example.katturaiproj

import android.app.Application
//import com.example.katturaiproj.model.Favorite.AppContainer
//import com.example.katturaiproj.model.Favorite.AppDataContainer
//import com.example.katturaiproj.model.Favorite.AppContainer
//import com.example.katturaiproj.model.Favorite.AppDataContainer
import com.example.katturaiproj.model.Favorite.Graph

class KaturaiApplication: Application(){

//    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
//        container = AppDataContainer(this)
        Graph.provide(this)
    }
}