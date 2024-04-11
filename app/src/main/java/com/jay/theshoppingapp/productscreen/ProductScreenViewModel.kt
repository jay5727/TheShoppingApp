package com.jay.theshoppingapp.productscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.theshoppingapp.core.result.Result
import com.jay.theshoppingapp.di.dispatchers.Dispatchers
import com.jay.theshoppingapp.productscreen.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ProductScreenViewModel @Inject constructor(
    @Dispatchers.IODispatcher private val ioDispatcher: CoroutineContext,
    private val productRepository: ProductRepository
) : ViewModel() {

    val _uiState: MutableStateFlow<ProductScreenUiState> = MutableStateFlow(ProductScreenUiState())
    val uiState: StateFlow<ProductScreenUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(title = "Product")
        }

        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch(ioDispatcher) {
            productRepository.fetchProducts().collect { res ->
                when (res) {
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                loading = false,
                                error = Error("Something went Wrong")
                            )
                        }
                    }

                    is Result.Loading -> {
                        _uiState.update {
                            it.copy(loading = true)
                        }
                    }

                    is Result.NoInternet -> {
                        _uiState.update {
                            it.copy(
                                loading = false,
                                error = Error("Please check internet connection...")
                            )
                        }
                    }

                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                loading = false,
                                products = ImmutableList(res.data)
                            )
                        }
                    }
                }
            }
        }
    }

}