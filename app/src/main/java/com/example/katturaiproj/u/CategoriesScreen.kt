package com.example.katturaiproj.u

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.katturaiproj.HandleBackButton
import com.example.katturaiproj.KatturaiAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    tabIndex : Int,
    navController: NavController,
    modifier: Modifier = Modifier
){

    val viewModel:CategoriesScreenViewModel = viewModel()

    HandleBackButton(navController = navController)

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            KatturaiAppBar(
                tabIndex = tabIndex,
                scrollBehavior = scrollBehavior,
                navController = navController
            ) }
    ) {
        Surface(
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
//            Text(viewModel.categoriesResponse.value.toString())
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(viewModel.categoriesResponse.value.categories){
                    AsyncImage(
                        model = "https://cdn1.kadalpura.com/articles/ta/CategoryImages/category_${it.id}.png",
                        contentDescription = it.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()

                    )
                }
            }
        }
    }
}