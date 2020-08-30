package com.sergiofierro.meli.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergiofierro.meli.model.Product
import com.sergiofierro.meli.mvvm.product.ProductAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
  view.adapter = adapter
}

@BindingAdapter("productList")
fun bindProductList(view: RecyclerView, products: List<Product>?) {
  products?.let {
    (view.adapter as? ProductAdapter)?.addProducts(it)
  }
}
