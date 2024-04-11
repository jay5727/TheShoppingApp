package com.jay.theshoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jay.theshoppingapp.productscreen.ProductScreenViewModel
import com.jay.theshoppingapp.productscreen.ProductScreen
import com.jay.theshoppingapp.ui.theme.TheShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TheShoppingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(navController, startDestination = "ProductsScreen") {
                        composable("ProductsScreen") {
                            ProductScreen(
                                modifier = Modifier.fillMaxSize(),
                                productScreenViewModel = hiltViewModel<ProductScreenViewModel>()
                            )
                        }
                    }
                }
            }
        }
    }
}
