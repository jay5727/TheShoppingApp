@file:OptIn(ExperimentalMaterial3Api::class)

package com.jay.theshoppingapp.productscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jay.theshoppingapp.core.compose.MobilePreview
import com.jay.theshoppingapp.productscreen.component.Product

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    productScreenViewModel: ProductScreenViewModel
) {
//    val snackbarHostState = remember { SnackbarHostState() }
//    LaunchedEffect(userScreenViewModel.uiStates.value.error) {
//        userScreenViewModel.uiStates.value.error?.let {
//            snackbarHostState.showSnackbar(
//                it.message ?: "Something went wrong",
//                duration = SnackbarDuration.Long
//            )
//        }
//    }
  
    Scaffold(
        //snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = productScreenViewModel.uiStates.value.title) })
        }
    ) {
//        if (userScreenViewModel.uiStates.value.error != null){
//           Text(text =userScreenViewModel.uiStates.value.error!!.message ?: "Something went wrong")
//        }
        if (productScreenViewModel.uiStates.value.loading) {
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
            LazyVerticalGrid(
                modifier = Modifier.padding(top = it.calculateTopPadding()),
                contentPadding = PaddingValues(horizontal = 8.dp),
                //columns = GridCells.Fixed(2),
                columns = GridCells.Adaptive(minSize = 128.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    items(
                        items = productScreenViewModel.uiStates.value.products,
                        key = { it.id }
                    ) {
                        Product(
                            modifier = Modifier.fillMaxWidth(), product = it,
                            productScreenViewModel::onProductClicked
                        )
                    }
                }
            )
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