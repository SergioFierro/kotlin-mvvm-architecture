package com.sergiofierro.meli.mvvm.product

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.google.android.material.snackbar.Snackbar
import com.sergiofierro.meli.R
import com.sergiofierro.meli.databinding.ActivityProductListingBinding
import com.sergiofierro.meli.mvvm.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProductListingActivity : BaseActivity() {
  @VisibleForTesting
  val viewModel: ProductListingViewModel by viewModels()
  private val binding: ActivityProductListingBinding by binding(R.layout.activity_product_listing)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.apply {
      lifecycleOwner = this@ProductListingActivity
      adapter = ProductAdapter(this@ProductListingActivity)
      vm = viewModel
    }

    Snackbar.make(findViewById(android.R.id.content), getString(R.string.start_searching), Snackbar.LENGTH_SHORT).show()
  }
}
