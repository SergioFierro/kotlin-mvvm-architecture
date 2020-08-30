package com.sergiofierro.meli.mvvm

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sergiofierro.meli.model.Product
import com.sergiofierro.meli.mvvm.product.ProductListingViewModel
import com.sergiofierro.meli.repository.ProductsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class ProductListingViewModelTest {

  private lateinit var viewModel: ProductListingViewModel
  private val repository: ProductsRepository = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @ExperimentalCoroutinesApi
  @Before
  fun setup() {
    viewModel = ProductListingViewModel(repository)
  }

  @Test
  fun `Fetch products should update the data`() = runBlocking {
    val expectedProducts: List<Product> = mock()
    val observer: Observer<List<Product>> = mock()
    viewModel.productListLiveData.observeForever(observer)
    val flow = flow {
      emit(expectedProducts)
    }
    val query = "MeLi"
    whenever(repository.fetchProducts(eq(query))).thenReturn(flow)
    viewModel.fetchProducts(query)
    delay(500L)
    verify(observer).onChanged(expectedProducts)
    assertFalse(viewModel.isLoading.get())
    assertNull(viewModel.alertLiveData.value)
    viewModel.productListLiveData.removeObserver(observer)
  }

  @Test
  fun `Fetch products with error should update alert`() =
    runBlocking {
      val expectedErrorMessage = "Invalid"
      val expectedThrowable = IOException(expectedErrorMessage)
      val observer: Observer<List<Product>> = mock()
      viewModel.productListLiveData.observeForever(observer)
      val flow = flow {
        emit(expectedThrowable)
      }
      val query = "MeLi"
      viewModel.fetchProducts(query)
      assertFalse(viewModel.isLoading.get())
      assertNotNull(viewModel.alertLiveData.value)
      assertEquals(expectedErrorMessage, viewModel.alertLiveData.value)
    }
}
