package com.sergiofierro.meli.repository

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sergiofierro.meli.model.ProductsResponse
import com.sergiofierro.meli.network.ProductsClient
import java.io.IOException
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalTime
@ExperimentalCoroutinesApi
class ProductsRepositoryTest {

  private val productsClient: ProductsClient = mock()
  private val productsRepository = ProductsRepository(productsClient)

  @Test
  fun `Fetch products with success response should contain expected items`() {
    runBlocking {
      val expectedResponse = ProductsResponse(listOf(mock()))
      val query = "MeLi"
      whenever(productsClient.fetchProducts(query)).thenReturn(expectedResponse)
      productsRepository.fetchProducts(query).test {
        assertEquals(expectedResponse.results.size, expectItem().size)
        expectComplete()
      }
      verify(productsClient).fetchProducts(query)
    }
  }

  @InternalCoroutinesApi
  @Test
  fun `Fetch products with exception should fail`() {
    runBlocking {
      val expectedError = IOException()
      val query = "MeLi"
      given(productsClient.fetchProducts(query)).willAnswer {
        throw expectedError
      }
      productsRepository.fetchProducts(query).test {
        assertEquals(expectedError::class.java, expectError()::class.java)
      }
      verify(productsClient).fetchProducts(query)
    }
  }
}
