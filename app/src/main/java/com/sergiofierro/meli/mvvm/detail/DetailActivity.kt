package com.sergiofierro.meli.mvvm.detail

import android.os.Bundle
import androidx.core.view.ViewCompat
import com.sergiofierro.meli.R
import com.sergiofierro.meli.databinding.ActivityDetailBinding
import com.sergiofierro.meli.model.Product
import com.sergiofierro.meli.mvvm.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

  private val binding: ActivityDetailBinding by binding(R.layout.activity_detail)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val productExtra: Product = requireNotNull(intent.getParcelableExtra(PRODUCT))
    binding.apply {
      product = productExtra
      adapter = AttributesAdapter().apply { submitList(productExtra.attributes) }
      lifecycleOwner = this@DetailActivity
    }
    ViewCompat.setTransitionName(productName, TITLE)
    ViewCompat.setTransitionName(productImage, IMAGE)
    ViewCompat.setTransitionName(productPrice, PRICE)
  }

  companion object {
    const val PRODUCT = "PRODUCT"
    const val TITLE = "TITLE"
    const val IMAGE = "IMAGE"
    const val PRICE = "PRICE"
  }
}
