package com.jay.theshoppingapp.productscreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.theshoppingapp.di.dispatchers.Dispatchers
import com.jay.theshoppingapp.model.Product
import com.jay.theshoppingapp.productscreen.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import com.jay.theshoppingapp.core.result.Result
import kotlinx.coroutines.withContext

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    @Dispatchers.IODispatcher private val ioDispatcher: CoroutineContext,
    @Dispatchers.MainDispatcher private val mainDispatcher: CoroutineContext,
    private val productRepository: ProductRepository
) : ViewModel() {

    val uiStates: MutableState<ProductScreenUiState> = mutableStateOf(ProductScreenUiState())

    init {
        uiStates.value = uiStates.value.copy(
            title = "Product"
        )
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch(ioDispatcher) {
            productRepository.fetchProducts().collect {

                withContext(mainDispatcher){
                    when(it) {
                        is Result.Error -> {
                            uiStates.value = uiStates.value.copy(
                                error = Exception("Something went Wrong")
                            )
                        }
                        is Result.Loading -> {
                            uiStates.value = uiStates.value.copy(
                                loading = true
                            )
                        }
                        is Result.NoInternet -> {
                            uiStates.value = uiStates.value.copy(
                                loading = false,
                                error = Exception("Please check internet connection...")
                            )
                        }
                        is Result.Success -> {
                            uiStates.value = uiStates.value.copy(
                                loading = false
                                // products = it.data
                            )
                            uiStates.value.products.addAll(it.data)
                        }
                    }
                }


            }

        }
    }

    fun onProductClicked(it: Product) {
        Log.d("JAY",it.brand+"-------")
    }
}