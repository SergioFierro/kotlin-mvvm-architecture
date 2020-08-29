package com.sergiofierro.meli.network

import com.sergiofierro.meli.network.api.ProductsService
import javax.inject.Inject

class ProductsClient @Inject constructor(private val productsService: ProductsService) {

  suspend fun fetchProducts(query: String) = productsService.fetchProducts(query = query)
}
