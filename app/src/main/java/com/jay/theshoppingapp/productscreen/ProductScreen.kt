@file:OptIn(ExperimentalMaterial3Api::class)

package com.jay.theshoppingapp.productscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jay.theshoppingapp.core.compose.MobilePreview
import com.jay.theshoppingapp.productscreen.component.Product

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    productScreenViewModel: ProductScreenViewModel
) {

    val state by productScreenViewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = state.title) })
        }
    ) {
        if (state.loading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp)
                )
            }
        } else {
            if (state.error != null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error?.message ?: "Issue occurred",
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    //columns = GridCells.Fixed(2),
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        items(
                            items = state.products.items,
                            key = { it.id }
                        ) {
                            Product(
                                modifier = Modifier.fillMaxWidth(), product = it,
                            )
                        }
                    }
                )
            }
        }
    }
}

@MobilePreview
@Composable
fun PreviewUsersScreen() {
    UsersScreen(
        modifier = Modifier.fillMaxSize(),
        productScreenViewModel = hiltViewModel<ProductScreenViewModel>()
    )
}