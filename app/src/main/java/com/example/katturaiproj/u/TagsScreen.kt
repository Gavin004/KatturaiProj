package com.example.katturaiproj.u

import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.katturaiproj.HandleBackButton
import com.example.katturaiproj.KatturaiAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsScreen(
    tabIndex:Int,
    navController: NavController,
    modifier: Modifier = Modifier
){

    HandleBackButton(navController = navController) // when back button is pressed it directly goes to home screen

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val viewModel:TagsScreenViewModel = viewModel()

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
//            Text(viewModel.tags.value.toString())
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.tags.value.tags){
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffba1a1a)
                        )
                    ) {
                        Text(
                            text = it.name,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}