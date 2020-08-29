package com.sergiofierro.meli.network

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sergiofierro.meli.network.api.ProductsService
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ProductsClientTest {

  private val productsService: ProductsService = mock()
  private val productsClient = ProductsClient(productsService)

  @Test
  fun `Fetch products should call products service`() {
    runBlocking {
      val query = "Meli"
      productsClient.fetchProducts(query)
      verify(productsService).fetchProducts(query = query)
    }
  }
}
