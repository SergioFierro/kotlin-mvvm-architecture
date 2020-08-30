package com.sergiofierro.meli.repository

import com.sergiofierro.meli.network.ProductsClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productsClient: ProductsClient) {
  suspend fun fetchProducts(
    query: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    val response = productsClient.fetchProducts(query)
    response.suspendOnSuccess {
      data?.let {
        emit(it.results)
      } ?: emit(emptyList())
      onSuccess()
    }
      .onError {
        onError(message())
      }
      .onException {
        onError(message())
      }
  }.flowOn(Dispatchers.IO)
}
