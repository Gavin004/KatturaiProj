package com.example.katturaiproj.u

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.katturaiproj.model.HomeItems

@Composable
fun HomeScreen(
    navController: NavController,
    uiState: UiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (uiState) {
        is UiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is UiState.Success -> ResultScreen(
            navController = navController,
            uiState.homeItems,
            modifier = modifier.fillMaxWidth()
        )
        is UiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ResultScreen(
    navController: NavController,
    homeItems: List<HomeItems>,
    modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(Modifier.height(150.dp))
        HomeCard(
            navController = navController,
            homeItems = homeItems
        )
        Spacer(Modifier.height(40.dp))
    }
}

@Composable
fun HomeCard(
    navController: NavController,
    homeItems: List<HomeItems>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(homeItems){ item ->
            if(item.type == 12) {
                HomeCardSingleType12(
                    navController = navController,
                    homeItem = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if(item.type == 4){
                HomeCardSingleType4(
                    navController = navController,
                    homeItem = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (item.type == 13){
                HomeCardSingleType13(
                    navController = navController,
                    homeItem = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if(item.type == 14){
                HomeCardSingleType14(
                    navController = navController,
                    homeItem = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun HomeCardSingleType12(
    navController: NavController,
    homeItem: HomeItems,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        onClick = {navController.navigate("details/${homeItem.id}")},
        modifier = modifier.padding(bottom = 10.dp).fillMaxSize().background(Color.White),
    ) {
//        Text(text = homeItem.type.toString())
        Text(
            text = homeItem.title?:"NoTitle",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(2.dp)
        )
        AsyncImage(
            model = "https://cdn1.kadalpura.com/articles/ta/${homeItem.imageUrl}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(160.dp)
        )
        Text(text = homeItem.shortDesc?:"NoDesc")
        HorizontalDivider(
            color = Color.Black,
            thickness = 0.5.dp,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun HomeCardSingleType4(
    navController: NavController,
    homeItem: HomeItems,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = modifier.padding(bottom = 10.dp).fillMaxSize().background(Color.White),
    ) {
//        Text(text = homeItem.type.toString())
        Column {
            Row {
                Text(
                    text = homeItem.title?:"NoTitle",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(2.dp).weight(2f)
                )
                AsyncImage(
                    model = "https://cdn1.kadalpura.com/articles/ta/${homeItem.imageUrl}",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(50.dp).height(100.dp).weight(1f)
                )
            }
            Text(text = homeItem.shortDesc?:"NoDesc")
            HorizontalDivider(
                color = Color.Black,
                thickness = 0.5.dp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun HomeCardSingleType13(
    navController: NavController,
    homeItem: HomeItems,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = modifier.padding(bottom = 10.dp).fillMaxSize().background(Color.White),
    ) {

        Column {
            Row {
                AsyncImage(
                    model = "https://cdn1.kadalpura.com/articles/ta/CategoryImages/category_${homeItem.id1}.png",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(100.dp).weight(1f).padding(2.dp)
                )
                AsyncImage(
                    model = "https://cdn1.kadalpura.com/articles/ta/CategoryImages/category_${homeItem.id2}.png",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(100.dp).weight(1f).padding(2.dp)
                )
            }
            Row {
                AsyncImage(
                    model = "https://cdn1.kadalpura.com/articles/ta/CategoryImages/category_${homeItem.id3}.png",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(100.dp).weight(1f).padding(2.dp)
                )
                AsyncImage(
                    model = "https://cdn1.kadalpura.com/articles/ta/CategoryImages/category_${homeItem.id4}.png",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height(100.dp).weight(1f).padding(2.dp)
                )

            }
            HorizontalDivider(
                color = Color.Black,
                thickness = 0.5.dp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun HomeCardSingleType14(
    navController: NavController,
    homeItem: HomeItems,
    modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = modifier.padding(bottom = 10.dp).fillMaxSize().background(Color.White),
    ) {
//        Text(text = homeItem.type.toString())
        AsyncImage(
            model = "https://cdn1.kadalpura.com/articles/ta/${homeItem.imageUrl}",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(160.dp)
        )
        Text(
            text = homeItem.title?:"NoTitle",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
        )
        HorizontalDivider(
            color = Color.Black,
            thickness = 0.5.dp,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(text = "Loading")
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Error")
    }
}