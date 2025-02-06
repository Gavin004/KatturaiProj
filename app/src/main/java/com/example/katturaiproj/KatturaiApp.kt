package com.example.katturaiproj

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.katturaiproj.u.HomeScreen
import com.example.katturaiproj.u.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatturaiApp(
    navController: NavController
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { KatturaiAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val homeViewModel:HomeViewModel  = viewModel()
            HomeScreen(
                navController = navController,
                uiState = homeViewModel.uiState,
                contentPadding = it
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatturaiAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf("HOME", "TRENDING", "NEW", "FAVOURITES", "CATEGORIES", "AUTHORS", "TAGS")
    Column(modifier = modifier) {

        CenterAlignedTopAppBar(
            scrollBehavior = scrollBehavior,
            title = {
                Text(
                    "கட்டுரைகள்",
                    modifier = Modifier.padding(2.dp)
                )
            },
            actions = {
                IconButton(onClick = {/*Handle search action*/ }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
            colors = TopAppBarColors(
                containerColor = Color(0xffba1a1a),
                titleContentColor = Color(0xfff7f9ff),
                actionIconContentColor = Color(0xfff7f9ff),
                scrolledContainerColor = Color(0xffba1a1a),
                navigationIconContentColor = Color(0xffba1a1a)
            )
        )

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0xffba1a1a),
            contentColor = Color(0xfff7f9ff),
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 4.dp,
                    color = Color(0xfff7f9ff)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) },
                    modifier = Modifier.padding(start = 36.dp, end = 36.dp)
                )
            }
        }
    }
}