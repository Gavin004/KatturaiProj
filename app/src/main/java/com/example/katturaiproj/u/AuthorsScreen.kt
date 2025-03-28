package com.example.katturaiproj.u

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.katturaiproj.HandleBackButton
import com.example.katturaiproj.KatturaiAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorsScreen(
    tabIndex : Int,
    navController: NavController,
    modifier: Modifier = Modifier
){

    HandleBackButton(navController = navController)

    val viewModel:AuthorsScreenViewModel = viewModel()

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
//            Text(viewModel.authorsResponse.value.toString())
            LazyColumn(
//                contentPadding = PaddingValues(16.dp)
            ) {
                items(viewModel.authorsResponse.value.authors){
                    Column {
                        Row(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
                            AsyncImage(
                                model = "https://cdn1.kadalpura.com/articles/author_image/${it.id}.png",
                                contentDescription = "AuthorImage",
                                modifier = Modifier.size(80.dp)
                            )
                            Column {
                                Text(text = it.name)
                                Text(text = it.title)
                            }
                        }
                        HorizontalDivider(
                            color = Color.Black,
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}