package com.sergiofierro.meli.repository

import com.sergiofierro.meli.model.Product
import com.sergiofierro.meli.network.ProductsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ProductsRepository @Inject constructor(private val productsClient: ProductsClient) {

  suspend fun fetchProducts(
    query: String
  ): Flow<List<Product>> {
    return flow {
      val response = productsClient.fetchProducts(query)
      emit(response.results)
    }.flowOn(Dispatchers.IO)
  }
}
