package com.sergiofierro.meli.mvvm.product

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.sergiofierro.meli.model.Product
import com.sergiofierro.meli.mvvm.base.BaseViewModel
import com.sergiofierro.meli.repository.ProductsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
class ProductListingViewModel @ViewModelInject constructor(private val productsRepository: ProductsRepository) : BaseViewModel() {

  private var productFetchingLiveData = MutableLiveData<String>()
  val productListLiveData: LiveData<List<Product>>
  val isLoading = ObservableBoolean(false)
  val alertLiveData = MutableLiveData<String>()

  init {
    productListLiveData = productFetchingLiveData.switchMap {
      launchOnViewModelScope {
        this.productsRepository.fetchProducts(it).onStart {
          isLoading.set(true)
        }.onCompletion {
          isLoading.set(false)
        }.catch { exception ->
          alertLiveData.postValue(exception.message)
        }.asLiveData()
      }
    }
  }

  fun fetchProducts(query: String) {
    productFetchingLiveData.value = query
  }
}
