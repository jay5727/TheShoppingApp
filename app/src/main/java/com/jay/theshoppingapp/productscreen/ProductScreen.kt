@file:OptIn(ExperimentalMaterial3Api::class)

package com.jay.theshoppingapp.productscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.TopAppBarDefaults
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
import com.jay.theshoppingapp.ui.theme.Purple40

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    productScreenViewModel: ProductScreenViewModel
) {

    val state by productScreenViewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = state.title, color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Purple40)
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues = it)) {
            if (state.loading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp)
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
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        content = {
                            items(
                                items = state.products.items,
                                key = { p -> p.id }
                            ) { product ->
                                Product(
                                    product = product,
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@MobilePreview
@Composable
fun PreviewUsersScreen() {
    ProductScreen(
        modifier = Modifier.fillMaxSize(),
        productScreenViewModel = hiltViewModel<ProductScreenViewModel>()
    )
}