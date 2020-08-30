package com.sergiofierro.meli.repository

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sergiofierro.meli.model.ProductsResponse
import com.sergiofierro.meli.network.ProductsClient
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class ProductsRepositoryTest {

  private val productsClient: ProductsClient = mock()
  private val productsRepository = ProductsRepository(productsClient)

  @Test
  fun `Fetch products with success response should contain expected items`() {
    runBlocking {
      val onSuccess: () -> Unit = mock()
      val onError: (String) -> Unit = mock()
      val expectedResponse = ProductsResponse(listOf(mock()))
      val query = "MeLi"
      whenever(productsClient.fetchProducts(query)).thenReturn(ApiResponse.of { Response.success(expectedResponse) })
      productsRepository.fetchProducts(query, onSuccess, onError).test {
        assertEquals(expectedResponse.results.size, expectItem().size)
        expectComplete()
      }
      verify(productsClient).fetchProducts(query)
      verify(onSuccess).invoke()
      verify(onError, never()).invoke(any())
    }
  }

  @InternalCoroutinesApi
  @Test
  fun `Fetch products with error should call onError`() {
    val onSuccess: () -> Unit = mock()
    val onError: (String) -> Unit = mock()
    runBlocking {
      val query = "MeLi"
      whenever(productsClient.fetchProducts(query)).thenReturn(ApiResponse.error(Throwable()))
      productsRepository.fetchProducts(query, onSuccess, onError).collect(mock())

      verify(productsClient).fetchProducts(query)
      verify(onError).invoke(any())
      verify(onSuccess, never()).invoke()
    }
  }
}
