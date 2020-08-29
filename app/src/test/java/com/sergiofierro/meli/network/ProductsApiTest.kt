package com.sergiofierro.meli.network

import com.nhaarman.mockitokotlin2.mock
import com.sergiofierro.meli.network.api.ProductsService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
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
    val responseBody = requireNotNull((response as ApiResponse.Success).data)
    mockWebServer.takeRequest()

    client.fetchProducts("query")
    assertTrue(responseBody.results.isNotEmpty())
  }

  @Test
  fun `Fetch products with invalid response should fail`() = runBlocking {
    enqueueMockResponse("invalid_response.json")
    val response = service.fetchProducts(query = "query")
    assertTrue(response is ApiResponse.Failure.Exception)
    assertNotNull((response as ApiResponse.Failure.Exception).message)
  }
}
