package com.sergiofierro.meli.network

import com.nhaarman.mockitokotlin2.mock
import com.sergiofierro.meli.network.api.ProductsService
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductsApiTest : BaseApiTest<ProductsService>() {

  private lateinit var service: ProductsService
  private val client: ProductsClient = mock()

  @Before
  fun setup() {
    service = createService(ProductsService::class.java)
  }

  @Test
  fun `Fetch products with valid response should succeed`() = runBlocking {
    enqueueMockResponse("product_search_response.json")
    val response = service.fetchProducts(query = "query")
    mockWebServer.takeRequest()

    client.fetchProducts("query")
    assertTrue(response.results.isNotEmpty())
  }

  @Test(expected = JsonDataException::class)
  fun `Fetch products with invalid response should fail`() {
    runBlocking {
      enqueueMockResponse("invalid_response.json")
      service.fetchProducts(query = "query")
    }
  }
}
