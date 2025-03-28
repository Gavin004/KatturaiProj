package com.example.katturaiproj.u

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.katturaiproj.HandleBackButton
import com.example.katturaiproj.KatturaiAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    tabIndex:Int,
    navController: NavController,
    modifier: Modifier = Modifier
){

    HandleBackButton(navController = navController)

    val viewModel:FavoritesScreenViewModel = viewModel()

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
//            Text(viewModel.favoriteListState.collectAsState().value.toString())
            val favUiState = viewModel.favoriteListState.collectAsState().value
            LazyColumn {
                items(favUiState){
                    Column(modifier = Modifier.clickable {
                        navController.navigate("details/${it.id}")
                    }) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = it.title,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.weight(2f)
                            )

                            AsyncImage(
                                model = "https://cdn1.kadalpura.com/articles/ta/${it.imgUrl}",
                                contentDescription = it.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.weight(1f).fillMaxWidth().height(64.dp)
                            )
                        }
                        Text(text = it.desc)
                        HorizontalDivider(
                            color = Color.Black,
                            thickness = 0.5.dp,
                            modifier = Modifier.padding( 8.dp)
                        )
                    }
                }
            }
        }
    }
}