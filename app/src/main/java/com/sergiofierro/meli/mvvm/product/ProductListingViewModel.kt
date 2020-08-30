package com.sergiofierro.meli.mvvm.product

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.sergiofierro.meli.extensions.clearFocus
import com.sergiofierro.meli.extensions.requestFocus
import com.sergiofierro.meli.model.Product
import com.sergiofierro.meli.mvvm.base.BaseViewModel
import com.sergiofierro.meli.repository.ProductsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
class ProductListingViewModel @ViewModelInject constructor(private val productsRepository: ProductsRepository) : BaseViewModel() {

  var productFetchingLiveData = MutableLiveData<String>()
  val productListLiveData: LiveData<List<Product>>
  val isLoading = ObservableBoolean(false)
  val alertLiveData = MutableLiveData<String>()
  val editorActionListener = TextView.OnEditorActionListener { editText, actionId, _ ->
    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
      editText.clearFocus(true)
      searchProducts(editText.text.toString())
      true
    } else false
  }

  init {
    productListLiveData = productFetchingLiveData.switchMap {
      launchOnViewModelScope {
        productsRepository.fetchProducts(it).onStart {
          isLoading.set(true)
        }.onCompletion {
          isLoading.set(false)
        }.catch { exception ->
          alertLiveData.postValue(exception.message)
        }.asLiveData()
      }
    }
  }

  fun search(editText: EditText) {
    if (editText.hasFocus()) {
      editText.clearFocus(true)
      searchProducts(editText.text.toString())
    } else {
      editText.requestFocus(true)
    }
  }

  @VisibleForTesting
  fun searchProducts(query: String?) {
    productFetchingLiveData.value = query
  }
}
